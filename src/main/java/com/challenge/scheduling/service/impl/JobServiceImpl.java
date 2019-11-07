package com.challenge.scheduling.service.impl;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.service.interfaces.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Override
    public List<Job> getAllJobs() {
        return null;
    }

    @Override
    public Job findJobById() {
        return null;
    }

    @Override
    public Job createJob(Job job) {
        return null;
    }

    @Override
    public Job updateJob(long jobId, Job job) {
        return null;
    }

    @Override
    public Boolean deleteJob(long jobId) {
        return null;
    }
}
