package org.ats.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JobRequest {
    private Long id;
    private String title;
    private String description;
    private String location;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private LocalDate deadline;
    private Long departmentId;
    private List<Long> skills;
}
