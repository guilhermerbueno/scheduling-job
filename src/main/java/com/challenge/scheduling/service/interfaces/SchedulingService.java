package com.challenge.scheduling.service.interfaces;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.model.Scheduling;

import java.time.LocalDateTime;
import java.util.List;

public interface SchedulingService {
    Scheduling generateScheduling(List<Job> jobs, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
}
