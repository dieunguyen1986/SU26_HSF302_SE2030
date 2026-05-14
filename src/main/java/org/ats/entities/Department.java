package org.ats.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "departments")
@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
@ToString
public class Department extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", unique = true,nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String departmentName;

    @Column(columnDefinition = "TEXT")
    private String description;

}
