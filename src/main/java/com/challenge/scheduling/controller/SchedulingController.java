package com.challenge.scheduling.controller;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.model.Scheduling;
import com.challenge.scheduling.service.interfaces.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/full")
    public ResponseEntity generateFullScheduling(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                             @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                             @Valid @RequestBody(required = false) List<Job> jobs) throws Exception {
        try {
            return ResponseEntity.ok(schedulingService.generateScheduling(jobs, startDate, endDate));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity generateScheduling(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                             @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                             @Valid @RequestBody(required = false) List<Job> jobs) throws Exception {
        try {
            return ResponseEntity.ok(schedulingService.generateScheduling(jobs, startDate, endDate).getJobIds());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
