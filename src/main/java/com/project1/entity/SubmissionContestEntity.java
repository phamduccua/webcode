package com.project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="submission_contest")
public class SubmissionContestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="submitted")
    private String submitted;

    @Column(name="language")
    private String language;

    @Column(name="status")
    private String status;

    @Column(name="execution_time")
    private Double executionTime;

    @Column(name="memory_used")
    private Long memoryUsed;

    @Column(name="submitted_at")
    private Date submittedAt;

    @Column(name="code")
    private String code;

    @Column(name="error")
    private String error;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="problem_id")
    private ProblemEntity problem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity user;
}
