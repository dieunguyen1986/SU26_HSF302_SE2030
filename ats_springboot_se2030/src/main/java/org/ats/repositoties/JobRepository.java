package org.ats.repositoties;

import org.ats.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {
    List<Job> findByTitle(String title);
    Boolean existsByTitle(String name);

    @Query("FROM Job j " +
            "WHERE (j.title LIKE ?1 OR j.description LIKE ?1) AND j.status = 'PUBLISHED'")
    Page<Job> findJobByPage(Pageable pageable, String keyword);

    Page<Job> findByStatus(Pageable pageable, String status);
}
