package com.challenge.scheduling.service.interfaces;

import com.challenge.scheduling.model.Scheduling;

import java.time.LocalDateTime;

public interface SchedulingService {
    Scheduling generateScheduling(LocalDateTime startDate, LocalDateTime endDate) throws Exception;
}
