package com.challenge.scheduling.model;

import java.util.List;

public class Scheduling {
    private List<List<Job>> jobScheduling;

    public List<List<Job>> getJobScheduling() {
        return jobScheduling;
    }

    public void setJobScheduling(List<List<Job>> jobScheduling) {
        this.jobScheduling = jobScheduling;
    }
}
