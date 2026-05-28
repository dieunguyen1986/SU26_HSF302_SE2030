package org.ats.dao;

import org.ats.entities.Job;

public interface JobDao {
    Job createJob(Job job);

    boolean isExisted(String title);
}
