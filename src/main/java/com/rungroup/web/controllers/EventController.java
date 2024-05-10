package com.rungroup.web.controllers;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.security.SecurityUtil;
import com.rungroup.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.rungroup.web.models.Event;
import com.rungroup.web.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
    private UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        List<EventDto> eventDtos = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", eventDtos);
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model) {
        UserEntity user = new UserEntity();
        EventDto eventDto = eventService.findEventById(eventId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @Valid @ModelAttribute("event")EventDto eventDto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateClub(@PathVariable("eventId") Long eventId,
                             @Valid @ModelAttribute("event") EventDto eventDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-edit";
        }
        EventDto eventDto1 = eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(eventDto1.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }
}
