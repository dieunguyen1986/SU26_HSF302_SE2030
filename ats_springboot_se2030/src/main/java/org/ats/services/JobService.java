package org.ats.services;

import org.ats.dto.JobRequest;
import org.ats.entities.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    Job createJob(JobRequest jobRequest);

    Job updateJob(JobRequest jobRequest);

    List<Job> findAll();

    void delete(Long id);

    Page<Job> search(String keyword, Integer pageIndex, Integer pageSize);
    public JobRequest findById(Long id);
}
