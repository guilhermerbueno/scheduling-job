package com.challenge.scheduling.service.impl;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.model.Scheduling;
import com.challenge.scheduling.service.interfaces.JobService;
import com.challenge.scheduling.service.interfaces.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private JobService jobService;

    @Override
    public Scheduling generateScheduling(LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        List<Job> allJobsToSchedule = jobService.getAllJobs();
        long avaialableDays = ChronoUnit.DAYS.between(startDate, endDate);
        validateJobs(allJobsToSchedule, startDate, endDate);
        return runScheduling(allJobsToSchedule, avaialableDays);
    }

    private void validateJobs(List<Job> jobs, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        for(Job job : jobs){
            if(job.getLimitDate().isBefore(startDate)){
                throw new Exception("Job " + job.getId() + " is out of required period");
            }
        }
    }

    private void validatePeriod(long necessaryDays, long availableDays) throws Exception {
        if(necessaryDays > availableDays){
            throw new Exception("It's not possible to schedule the jobs in the required period! The period has " + availableDays + " days and it's necessary " + necessaryDays + "days!");
        }
    }

    private Scheduling runScheduling(List<Job> allJobsToSchedule, long availableDays) throws Exception {
        List<Job> jobsDay = new ArrayList<>();
        Scheduling scheduling = new Scheduling();
        long necessaryDays = 0;

        while(!allJobsToSchedule.isEmpty()){
            Job job = findLowerFinishJob(allJobsToSchedule);

            int duration = calculateJobsDuration(jobsDay, job);
            if(scheduling.checkDailyCapacity(duration)){
                jobsDay.add(job);
            } else {
                scheduling.getJobScheduling().add(new ArrayList<>(jobsDay));
                necessaryDays += 1;
                validatePeriod(necessaryDays, availableDays);
                jobsDay.clear();
                jobsDay.add(job);
            }

            allJobsToSchedule.remove(job);
        }

        scheduling.getJobScheduling().add(new ArrayList<>(jobsDay));
        necessaryDays += 1;
        validatePeriod(necessaryDays, availableDays);

        scheduling.print();
        return scheduling;
    }

    /**
     * Find the first task that should be finished
     *
     * @param jobs
     * @return the job with the lowest finished date
     */
    private Job findLowerFinishJob(List<Job> jobs){
        Job lowerFinishJob = jobs.get(0);
        for (int i = 1; i < jobs.size(); i++){
            if(jobs.get(i).getLimitDate().isBefore(lowerFinishJob.getLimitDate())){
                lowerFinishJob = jobs.get(i);
            }

            if(jobs.get(i).getLimitDate().isEqual(lowerFinishJob.getLimitDate())){
                if(jobs.get(i).getEstimatedDuration() < lowerFinishJob.getEstimatedDuration()){
                    lowerFinishJob = jobs.get(i);
                }
            }
        }

        return lowerFinishJob;
    }

    /**
     * Check if the job could be do at the current day or should be postponed to the next day
     *
     * @param job
     * @return <b>True</b> if could be done at the current day or <b>False</b> if should be postponed
     */
    private int calculateJobsDuration(List<Job> jobs, Job job){
        int duration = job.getEstimatedDuration();
        for(Job previousJob : jobs){
            duration += previousJob.getEstimatedDuration();
        }
        return duration;
    }
}
