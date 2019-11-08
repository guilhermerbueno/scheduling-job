package com.challenge.scheduling.service.interfaces;

import com.challenge.scheduling.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    Job findJobById(long jobId);

    List<Job> createJobs(List<Job> jobs);

    Job updateJob(long jobId, Job job);

    Boolean deleteJob(long jobId);
}
