package com.challenge.scheduling.service;

import com.challenge.scheduling.model.Job;
import com.challenge.scheduling.model.Scheduling;
import com.challenge.scheduling.service.impl.SchedulingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SchedulingServiceTest {

    @Autowired
    private SchedulingServiceImpl schedulingService;

    @Test
    public void givenJobsAndPeriod_whenGenerateScheduling_thenOk() throws Exception {
        List<Job> jobs = createJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 08:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-11 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
        assertEquals(1, scheduling.getJobScheduling().get(0).get(0).getId());
        assertEquals(3, scheduling.getJobScheduling().get(0).get(1).getId());
        assertEquals(2, scheduling.getJobScheduling().get(1).get(0).getId());
    }

    @Test(expected = Exception.class)
    public void givenJobsAndPeriod_whenJobLimitIsBeforeThePeriod_thenFail() throws Exception {
        List<Job> jobs = createJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(4,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-09 12:00:00", formatter), 2));
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 08:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-11 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
    }

    @Test
    public void givenJobsAndPeriod_whenJobLimitIsAfterThePeriod_thenOk() throws Exception {
        List<Job> jobs = createJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(4,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-12 12:00:00", formatter), 2));
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 08:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-11 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
    }

    @Test(expected = Exception.class)
    public void givenJobsAndPeriod_whenTheJobsTakenMoreTimeThanPeriod_thenFail() throws Exception {
        List<Job> jobs = createJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(4,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-11 12:00:00", formatter), 2));
        jobs.add(new Job(5,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-12 12:00:00", formatter), 6));
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 08:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-11 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
    }

    @Test(expected = Exception.class)
    public void givenJobsAndPeriod_whenTheJobEstimatedDurationIsGreaterThanAllowed_thenFail() throws Exception {
        List<Job> jobs = createJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(4,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-10 12:00:00", formatter), 10));
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 08:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-11 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
    }

    @Test
    public void givenJobsAndPeriod_whenEqualsDateLimitTheFastestComesFirst_thenFail() throws Exception {
        List<Job> jobs = createJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(4,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-11 12:00:00", formatter), 2));
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 08:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-11 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
        assertEquals(4, scheduling.getJobScheduling().get(1).get(0).getId());
    }


    @Test
    public void givenJobsAndPeriod_whenJobStartsAtOneDayAndFinishInTheNext_thenOk() throws Exception {
        List<Job> jobs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(1,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-10 21:00:00", formatter), 2));
        jobs.add(new Job(2,"Importação de dados da Base Legada", LocalDateTime.parse("2019-11-11 12:00:00", formatter), 6));
        jobs.add(new Job(3,"Importação de dados de integração", LocalDateTime.parse("2019-11-11 08:00:00", formatter), 4));
        jobs.add(new Job(4,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-11 12:00:00", formatter), 2));
        LocalDateTime startDate = LocalDateTime.parse("2019-11-10 18:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2019-11-12 18:00:00", formatter);

        Scheduling scheduling = schedulingService.generateScheduling(jobs, startDate, endDate);

        assertEquals(2, scheduling.getJobScheduling().size());
    }


    private List<Job> createJobs(){
        List<Job> jobs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jobs.add(new Job(1,"Importação de arquivos de fundos", LocalDateTime.parse("2019-11-10 12:00:00", formatter), 2));
        jobs.add(new Job(2,"Importação de dados da Base Legada", LocalDateTime.parse("2019-11-11 12:00:00", formatter), 4));
        jobs.add(new Job(3,"Importação de dados de integração", LocalDateTime.parse("2019-11-11 08:00:00", formatter), 6));

        return jobs;
    }
}
