package com.travelplanner.service;

import com.travelplanner.dto.ActivityDTO;
import com.travelplanner.model.Activity;
import com.travelplanner.model.Destination;
import com.travelplanner.repository.ActivityRepository;
import com.travelplanner.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final DestinationRepository destinationRepository;

    public Activity addActivity(ActivityDTO activityDTO) {
        Destination destination = destinationRepository.findById(activityDTO.getDestinationId())
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        Activity activity = new Activity();
        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());
        activity.setDate(activityDTO.getDate());
        activity.setTime(LocalTime.parse(activityDTO.getTime()));
        activity.setCost(activityDTO.getCost());
        activity.setDestination(destination);

        return activityRepository.save(activity);
    }

    public List<Activity> getDestinationActivities(Long destinationId) {
        return activityRepository.findByDestination_Id(destinationId);
    }

    public void deleteActivity(Long activityId) {
        activityRepository.deleteById(activityId);
    }
}