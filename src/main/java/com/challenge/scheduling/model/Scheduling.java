package com.challenge.scheduling.model;

import java.util.ArrayList;
import java.util.List;

public class Scheduling {
    private List<List<Job>> jobScheduling;

    private int dailyCapacity = 8;

    public Scheduling() {
        jobScheduling = new ArrayList<>();
    }

    public List<List<Job>> getJobScheduling() {
        return jobScheduling;
    }

    public void setJobScheduling(List<List<Job>> jobScheduling) {
        this.jobScheduling = jobScheduling;
    }

    public int getDailyCapacity() {
        return dailyCapacity;
    }

    public void setDailyCapacity(int dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }

}
