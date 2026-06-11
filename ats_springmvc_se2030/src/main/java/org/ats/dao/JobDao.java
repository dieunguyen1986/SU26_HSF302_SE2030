package org.ats.dao;

import org.ats.entities.Job;

import java.util.List;

public interface JobDao {
    Job createJob(Job job);

    boolean isExisted(String title);

    List<Job> search(String keyword);

    List<Job> findAll();

    public Job findById(Long id);
}
