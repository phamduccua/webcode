package com.project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="testcase")
public class TestCaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="example")
    private String example;

    @Column(name="type")
    private String type;

    @Column(name="output_file_name")
    private String outputFileName;

    @Column(name="expected_output_file_content")
    private String expctedOutputFileContent;

    @OneToMany(mappedBy = "testCases", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InputEntity> inputs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="problem_id")
    private ProblemEntity problem;
}
