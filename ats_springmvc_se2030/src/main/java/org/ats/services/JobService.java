package org.ats.services;

import org.ats.dto.JobRequest;
import org.ats.entities.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobRequest jobRequest);

    Job updateJob(Job job);

    List<Job> findAll();

    void delete(Long id);

    List<Job> search(String keyword);
    public Job findById(Long id);
}
