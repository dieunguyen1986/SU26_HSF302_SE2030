package org.ats.config;

import org.ats.dao.JobDao;
import org.ats.dao.JobDaoImpl;
import org.ats.services.JobService;
import org.ats.services.JobServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("appConfig")
public class AppConfig {

    @Bean
    public JobDao jobDao() {
        return new JobDaoImpl();
    }

    @Bean
    public JobService jobService() {
        return new JobServiceImpl(jobDao());
    }
}
