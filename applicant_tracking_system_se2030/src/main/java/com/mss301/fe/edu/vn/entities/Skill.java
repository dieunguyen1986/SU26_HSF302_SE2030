package com.mss301.fe.edu.vn.entities;

import jakarta.persistence.*;
import lombok.*;
import org.ats.entities.BaseEntity;
import org.ats.entities.Job;
import org.ats.entities.JobSkill;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    private String category;

    @OneToMany(mappedBy = "skill", cascade =  CascadeType.ALL, orphanRemoval = true)
    private Set<JobSkill> jobSkills = new HashSet<>();
}