package com.project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name="submission")
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="submitted")
    private String submitted;

    @Column(name="language")
    private String language;

    @Column(name="status")
    private int status;

    @Column(name="execution_time")
    private double executionTime;

    @Column(name="memory_used")
    private Long memoryUsed;

    @Column(name="submitted_at")
    private Date submittedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="problem_id")
    private ProblemEntity problem;
}
