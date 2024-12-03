package com.travelplanner.service;

import com.travelplanner.dto.DestinationDTO;
import com.travelplanner.exception.ResourceNotFoundException;
import com.travelplanner.model.Destination;
import com.travelplanner.model.Trip;
import com.travelplanner.repository.DestinationRepository;
import com.travelplanner.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final TripRepository tripRepository;

    public List<Destination> getTripDestinations(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new ResourceNotFoundException("Trip not found with id: " + tripId);
        }
        return destinationRepository.findByTripId(tripId);
    }

    public Destination addDestination(DestinationDTO dto) {
        Trip trip = tripRepository.findById(dto.getTripId())
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        Destination destination = new Destination();
        destination.setName(dto.getName());
        destination.setDescription(dto.getDescription());
        destination.setAddress(dto.getAddress());
        destination.setTrip(trip);

        return destinationRepository.save(destination);
    }

    public void deleteDestination(Long id) {
        Destination destination = getDestination(id);
        destinationRepository.delete(destination);
    }

    public Destination getDestination(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
    }

    public Destination updateDestination(Long id, DestinationDTO destinationDTO) {
        Destination destination = getDestination(id);
        
        if (destinationDTO.getName() != null && !destinationDTO.getName().trim().isEmpty()) {
            destination.setName(destinationDTO.getName().trim());
        }
        
        if (destinationDTO.getDescription() != null) {
            destination.setDescription(destinationDTO.getDescription().trim());
        }
        
        if (destinationDTO.getAddress() != null && !destinationDTO.getAddress().trim().isEmpty()) {
            destination.setAddress(destinationDTO.getAddress().trim());
        }

        return destinationRepository.save(destination);
    }
}
