package com.challenge.scheduling.controller;

import com.challenge.scheduling.model.Scheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @GetMapping
    public Scheduling generateScheduling(){
        return null;
    }
}
