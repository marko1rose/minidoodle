package com.doodle.minidoodle.repository;

import com.doodle.minidoodle.model.entities.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
}
