package com.project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="contest")
@Getter
@Setter

public class ContestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name="created_by")
    private Long created_by;

    @Column(name="status")
    private int status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="contest_problem",
        joinColumns = @JoinColumn(name="contest_id",nullable=false),
            inverseJoinColumns = @JoinColumn(name="problem_id", nullable=false))
    private List<ProblemEntity> problemEntities = new ArrayList<>();
}
