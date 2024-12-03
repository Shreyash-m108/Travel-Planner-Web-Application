package com.travelplanner.controller;

import com.travelplanner.dto.DestinationDTO;
import com.travelplanner.model.Destination;
import com.travelplanner.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping("/destinations/trip/{tripId}/new")
    public String showDestinationForm(@PathVariable Long tripId, Model model) {
        DestinationDTO destination = new DestinationDTO();
        destination.setTripId(tripId);
        model.addAttribute("destination", destination);
        return "destinations/form";
    }

    @PostMapping("/destinations")
    public String addDestination(@ModelAttribute DestinationDTO destinationDTO) {
        try {
            destinationService.addDestination(destinationDTO);
            return "redirect:/trips/" + destinationDTO.getTripId();
        } catch (Exception e) {
            return "redirect:/destinations/trip/" + destinationDTO.getTripId() + "/new";
        }
    }

    @GetMapping("/destinations/trip/{tripId}")
    public String listDestinations(@PathVariable Long tripId, Model model) {
        model.addAttribute("destinations", destinationService.getTripDestinations(tripId));
        model.addAttribute("tripId", tripId);
        return "destinations/list";
    }

    @DeleteMapping("/destinations/{id}")
    public String deleteDestination(@PathVariable Long id) {
        Destination destination = destinationService.getDestination(id);
        Long tripId = destination.getTrip().getId();
        destinationService.deleteDestination(id);
        return "redirect:/trips/" + tripId;
    }
}