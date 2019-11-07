package com.challenge.scheduling.model;

import java.util.Calendar;

public class Job {

    private long id;

    private String description;

    private Calendar limitDate;

    /**
     * The estimated duration of job execution in hours
     */
    private int estimatedDuration;

    public Job(String description, Calendar limitDate, int estimatedDuration) {
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

    public Calendar getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Calendar limitDate) {
        this.limitDate = limitDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }
}
