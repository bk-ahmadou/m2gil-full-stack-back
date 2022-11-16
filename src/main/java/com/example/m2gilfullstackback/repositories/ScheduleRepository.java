package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
