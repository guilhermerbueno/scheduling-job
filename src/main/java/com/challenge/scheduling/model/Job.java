package com.challenge.scheduling.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "limit_date")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime limitDate;

    /**
     * The estimated duration of job execution in hours
     */
    @NotNull
    @Column(name = "duration")
    private int estimatedDuration;

    public Job() {
    }

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
