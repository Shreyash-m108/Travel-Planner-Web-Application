package com.travelplanner.controller;

import com.travelplanner.dto.TripDTO;
import com.travelplanner.model.Trip;
import com.travelplanner.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping
    public String listTrips(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        model.addAttribute("trips", tripService.getUserTrips(userId));
        return "trips/list";
    }

    @GetMapping("/new")
    public String showTripForm(Model model) {
        model.addAttribute("trip", new TripDTO());
        return "trips/form";
    }

    @PostMapping
    public String createTrip(@ModelAttribute TripDTO tripDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        tripDTO.setUserId(userId);
        tripService.createTrip(tripDTO);
        return "redirect:/trips";
    }

    @GetMapping("/{tripId}")
    public String tripDetails(@PathVariable Long tripId, Model model) {
        model.addAttribute("trip", tripService.getTripById(tripId));
        return "trips/details";
    }

    @PutMapping("/{tripId}")
    public String updateTrip(@PathVariable Long tripId, @ModelAttribute TripDTO tripDTO) {
        tripService.updateTrip(tripId, tripDTO);
        return "redirect:/trips";
    }

    @PostMapping("/delete/{tripId}")
    public String deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return "redirect:/trips";
    }

    @GetMapping("/{tripId}/edit")
    public String showEditForm(@PathVariable Long tripId, Model model) {
        Trip trip = tripService.getTripById(tripId);
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        tripDTO.setTitle(trip.getTitle());
        tripDTO.setDescription(trip.getDescription());
        tripDTO.setStartDate(trip.getStartDate());
        tripDTO.setEndDate(trip.getEndDate());
        tripDTO.setUserId(trip.getUser().getId());
        
        model.addAttribute("trip", tripDTO);
        return "trips/form";
    }

    
}