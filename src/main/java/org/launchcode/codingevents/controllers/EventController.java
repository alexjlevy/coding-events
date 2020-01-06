package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")

public class EventController {

    @GetMapping
    public String displayAllEvents (Model model){
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent,
                              Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String renderDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    //lives at /events/edit
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId){
        model.addAttribute(EventData.getById(eventId));
        model.addAttribute("title", "Edit event ${event.name} (id=${event.id})");
        return "events/edit";
    }

    //lives at /events/edit
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description){
        Event newEvent = EventData.getById(eventId);
        newEvent.setName(name);
        newEvent.setDescription(description);
        return "redirect:/events";
    }
}
