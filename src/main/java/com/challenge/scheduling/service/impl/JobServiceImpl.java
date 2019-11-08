package com.challenge.scheduling.service.impl;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.repository.JobRepository;
import com.challenge.scheduling.service.interfaces.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(int jobId) {
        return jobRepository.getOne((int) jobId);
    }

    @Override
    public List<Job> createJobs(List<Job> jobs) {
        return jobRepository.saveAll(jobs);
    }

    @Override
    public Job updateJob(int jobId, Job job) {
        Job previousJob = jobRepository.getOne(jobId);
        previousJob.setDescription(job.getDescription());
        previousJob.setEstimatedDuration(job.getEstimatedDuration());
        previousJob.setLimitDate(job.getLimitDate());

        return jobRepository.save(previousJob);
    }

    @Override
    public Boolean deleteJob(int jobId) {
        Job previousJob = jobRepository.getOne((int) jobId);
        if(previousJob == null){
            return false;
        }

        jobRepository.delete(previousJob);
        return true;
    }
}
