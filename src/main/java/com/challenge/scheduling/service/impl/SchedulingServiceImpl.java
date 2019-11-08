package com.challenge.scheduling.service.impl;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.model.Scheduling;
import com.challenge.scheduling.service.interfaces.JobService;
import com.challenge.scheduling.service.interfaces.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private JobService jobService;

    @Override
    public Scheduling generateScheduling(List<Job> jobs, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        if(jobs == null || jobs.isEmpty()){
            jobs = jobService.getAllJobs();
        }
        return runScheduling(jobs, startDate, endDate);
    }

    private LocalDateTime validateJob(Job job, LocalDateTime currentDate, LocalDateTime endDate) throws Exception {
        currentDate = currentDate.plusHours(job.getEstimatedDuration());

        if(job.getLimitDate().isBefore(currentDate)){
            throw new Exception("The job " + job.getId() + " is out of required period");
        }
        if(currentDate.isAfter(endDate)){
            throw new Exception("The job "+ job.getId() + " couldn't be finish before the limit date");
        }

        return currentDate;
    }

    private Scheduling runScheduling(List<Job> allJobsToSchedule, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        List<Job> jobsDay = new ArrayList<>();
        Scheduling scheduling = new Scheduling();
        int necessaryTimeToday = 0;
        LocalDateTime currentDate = startDate;

        while(!allJobsToSchedule.isEmpty()){
            Job job = findLowerFinishJob(allJobsToSchedule);

            necessaryTimeToday += job.getEstimatedDuration();

            //job fits on current day
            if(scheduling.checkDailyCapacity(necessaryTimeToday)){
                currentDate = validateJob(job, currentDate, endDate);
                jobsDay.add(job);
            } else {
                scheduling.getJobScheduling().add(new ArrayList<>(jobsDay));
                jobsDay.clear();

                currentDate = startDate.plusDays(scheduling.getJobScheduling().size());
                currentDate = validateJob(job, currentDate, endDate);

                necessaryTimeToday = job.getEstimatedDuration();
                jobsDay.add(job);
            }

            allJobsToSchedule.remove(job);
        }

        scheduling.getJobScheduling().add(new ArrayList<>(jobsDay));

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
}
