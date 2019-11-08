package com.challenge.scheduling.model;

import java.util.ArrayList;
import java.util.List;

public class Scheduling {

    private List<List<Job>> jobScheduling;

    private int dailyCapacity = 8;

    public Scheduling() {
        jobScheduling = new ArrayList<>();
        this.getJobScheduling().clear();
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

    /**
     * Check if the job could be do at current day or should be postponed to the next
     *
     * @param jobsDuration
     * @return <b>True</b> if could be done at the current day or <b>False</b> if should be postponed
     */
    public boolean checkDailyCapacity(int jobsDuration){
        return jobsDuration <= getDailyCapacity();
    }

    public void print(){
        for(List<Job> dailyJobs : getJobScheduling()){
            List<Long> jobIds = new ArrayList<Long>();
            for(Job job : dailyJobs){
                jobIds.add(job.getId());
            }
            System.out.println(jobIds);
        }
    }
}
