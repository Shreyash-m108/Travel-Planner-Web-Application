package com.travelplanner.service;

import com.travelplanner.dto.TripDTO;
import com.travelplanner.model.Trip;
import com.travelplanner.model.User;
import com.travelplanner.repository.TripRepository;
import com.travelplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public Trip createTrip(TripDTO tripDTO) {
        User user = userRepository.findById(tripDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Trip trip = new Trip();
        trip.setTitle(tripDTO.getTitle());
        trip.setDescription(tripDTO.getDescription());
        trip.setStartDate(tripDTO.getStartDate());
        trip.setEndDate(tripDTO.getEndDate());
        trip.setUser(user);

        return tripRepository.save(trip);
    }

    public List<Trip> getUserTrips(Long userId) {
        return tripRepository.findByUser_Id(userId);
    }

    public Trip getTripById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    public Trip updateTrip(Long tripId, TripDTO tripDTO) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setTitle(tripDTO.getTitle());
        trip.setDescription(tripDTO.getDescription());
        trip.setStartDate(tripDTO.getStartDate());
        trip.setEndDate(tripDTO.getEndDate());
        return tripRepository.save(trip);
    }
}
