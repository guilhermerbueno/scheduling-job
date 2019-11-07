package com.challenge.scheduling;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.model.Scheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingApplication.class, args);
		List<Job> jobs = createJobs();
		generateScheduling(jobs);
	}

	private static void generateScheduling(List<Job> jobs){
		Scheduling scheduling = new Scheduling();
		List<Job> jobsDay = new ArrayList<>();
		while(!jobs.isEmpty()){

			Job job = findLowerFinishJob(jobs);
			boolean fitsCurrentDay = checkDailyCapacity(jobsDay, job);
			if(fitsCurrentDay){

			} else {

			}
		}
	}

	/**
	 * Find the first task that should be finished
	 *
	 * @param jobs
	 * @return the job with the lowest finished date
	 */
	private static Job findLowerFinishJob(List<Job> jobs){
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
	private static	boolean checkDailyCapacity(List<Job> jobs, Job job){
		return true;
	}

	private static List<Job> createJobs(){
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job(1,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-10 12:00:00"), 2));
		jobs.add(new Job(2,"Importação de dados da Base Legada", LocalDateTime.parse("2019-11-11 12:00:00"), 4));
		jobs.add(new Job(3,"Importação de dados de integração", LocalDateTime.parse("2019-11-11 08:00:00"), 6));

		return jobs;
	}
}
