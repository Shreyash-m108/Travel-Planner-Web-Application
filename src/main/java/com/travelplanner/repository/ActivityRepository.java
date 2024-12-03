package com.travelplanner.repository;

import com.travelplanner.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByDestination_Id(Long destinationId);
    List<Activity> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Activity> findByDestination_Trip_Id(Long tripId);
}