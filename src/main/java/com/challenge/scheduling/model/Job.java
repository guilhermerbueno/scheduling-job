package com.challenge.scheduling.model;

import java.time.LocalDateTime;

public class Job {

    private long id;

    private String description;

    private LocalDateTime limitDate;

    /**
     * The estimated duration of job execution in hours
     */
    private int estimatedDuration;

    public Job(long id, String description, LocalDateTime limitDate, int estimatedDuration) {
        this.id = id;
        this.description = description;
        this.limitDate = limitDate;
        this.estimatedDuration = estimatedDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.limitDate = limitDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }
}
