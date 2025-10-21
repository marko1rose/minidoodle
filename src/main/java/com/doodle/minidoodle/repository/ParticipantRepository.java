package com.doodle.minidoodle.repository;

import com.doodle.minidoodle.model.entities.ParticipantEntity;
import com.doodle.minidoodle.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    List<ParticipantEntity> findByUser(UserEntity user);
}
