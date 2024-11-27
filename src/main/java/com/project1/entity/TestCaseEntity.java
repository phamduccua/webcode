package com.project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="testcase")
public class TestCaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="input")
    private String input;

    @Column(name="expected_output")
    private String expected_output;

    @Column(name="example")
    private String example;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="problem_id")
    private ProblemEntity problem;
}
