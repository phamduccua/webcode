package com.project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="input")
public class InputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="file_name")
    private String fileName;
    @Column(name="content_file")
    private String contentFile;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="testcase_id", nullable = false)
    private TestCaseEntity testCases;
}
