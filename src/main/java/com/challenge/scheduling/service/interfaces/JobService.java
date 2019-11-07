package com.challenge.scheduling.service.interfaces;

import com.challenge.scheduling.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    Job findJobById();

    Job createJob(Job job);

    Job updateJob(long jobId, Job job);

    Boolean deleteJob(long jobId);
}
