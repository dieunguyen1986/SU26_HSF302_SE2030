package org.ats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"org.ats.dao", "org.ats.services"})
@EnableTransactionManagement
public class AppDataConfig {
    @Bean
    public DriverManagerDataSource dataSource() { // Spring Bean
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName
                ("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ats_db_se2030");
        dataSource.setUsername("dieunt");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() { // Spring Bean
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // DI
        sessionFactory.setPackagesToScan("org.ats.entities"); // mapping entity vs table
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // Cập nhật Dialect phù hợp với Hibernate 6 (Community khuyên dùng phiên bản chung)
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

}
