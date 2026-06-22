package org.ats.services;

import org.ats.dto.JobRequest;
import org.ats.entities.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobRequest jobRequest);

    Job updateJob(JobRequest jobRequest);

    List<Job> findAll();

    void delete(Long id);

    List<Job> search(String keyword);
    public JobRequest findById(Long id);
}
