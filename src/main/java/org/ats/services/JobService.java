package org.ats.services;

import org.ats.entities.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);

    Job updateJob(Job job);

    List<Job> findAll();

    void delete(Long id);

    List<Job> search(String keyword);
}
