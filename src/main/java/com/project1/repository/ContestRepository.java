package com.project1.repository;

import com.project1.entity.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
    List<ContestEntity> findAllByStatusAndEndTimeBefore(int status, LocalDateTime time);
    ContestEntity findContestById(Long id);
}
