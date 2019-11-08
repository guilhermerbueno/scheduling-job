package com.challenge.scheduling.controller;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.service.interfaces.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable("id") long jobId){
        return ResponseEntity.ok(jobService.findJobById(jobId));
    }

    @PostMapping
    public ResponseEntity<Job> createJobs(@Valid @RequestBody List<Job> jobs){
        jobService.createJobs(jobs);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") long jobId, @Valid @RequestBody Job job){
        return ResponseEntity.ok(jobService.updateJob(jobId, job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable("id") long jobId){
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }
}
