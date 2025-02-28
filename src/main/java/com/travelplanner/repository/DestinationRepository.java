package com.travelplanner.repository;

import com.travelplanner.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByTripId(Long tripId);
}
