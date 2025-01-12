package com.project1.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="problem")
public class ProblemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="difficulty")
    private String difficulty;

    @Column(name="input_format")
    private String inputFormat;

    @Column(name="output_format")
    private String outputFormat;

    @Column(name="code")
    private String code;

    @Column(name="type")
    private String type;

    @Column(name="example")
    private String example;

    @Column(name="topic")
    private String topic;

    @Column(name="class_id")
    private Long classId;

    @Column(name="constraints")
    private String constraints;

    @Column(name="color")
    private String color;

    @Column(name="time_limit")
    private float time_limit;

    @Column(name="memory_limit")
    private Long memory_limit;

    @Column(name="language")
    private String language;

    @OneToMany(mappedBy="problem", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TestCaseEntity> testCases = new ArrayList<>();

    @OneToMany(mappedBy="problem", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SubmissionEntity> submissions = new ArrayList<>();

    @ManyToMany(mappedBy="problemEntities", fetch = FetchType.LAZY)
    private List<ContestEntity> contestEntites = new ArrayList<>();
}
