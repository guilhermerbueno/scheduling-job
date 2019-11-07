package com.challenge.scheduling;

import com.challenge.scheduling.model.Job;
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

	}

	private static List<Job> createJobs(){
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job(1,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-10 12:00:00"), 2));
		jobs.add(new Job(2,"Importação de dados da Base Legada", LocalDateTime.parse("2019-11-11 12:00:00"), 4));
		jobs.add(new Job(3,"Importação de dados de integração", LocalDateTime.parse("2019-11-11 08:00:00"), 6));

		return jobs;
	}
}
