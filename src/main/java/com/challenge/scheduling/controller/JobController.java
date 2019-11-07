package com.challenge.scheduling.controller;

import com.challenge.scheduling.model.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @GetMapping
    public List<Job> getAllJobs(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(long jobId){
        return null;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(Job job){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> deleteJob(){
        return null;
    }
}
