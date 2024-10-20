package com.mora.booking.rest;

import com.mora.booking.pojo.Event;
import com.mora.booking.service.EventService;
import com.mora.booking.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/events")
public class EventController {

    private final EventService eventService;
    private final TicketService ticketService;

    public EventController(EventService eventService, TicketService ticketService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    // add a event
    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        try {
            Event event1 = eventService.addEvent(event);
            return new ResponseEntity<>(event1, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // release tickets for event
    @PostMapping("/{eventId}/relase")
    public ResponseEntity<?> releaseTickets(
            @PathVariable Long eventId,
            @PathVariable int totalTickets,
            @PathVariable int ticketReleaseRate
    ) {
        try {
            Event event = eventService.getEventById(eventId);
            eventService.releaseTickets(eventId, totalTickets);
            ticketService.startEventOrganizer(totalTickets, ticketReleaseRate);
            return new ResponseEntity<>(event.getEventName(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // get event by id
    @GetMapping("{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Long eventId) {
        try {
            Event event = eventService.getEventById(eventId);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public ResponseEntity<?> getEvents() {
        return null;
    }
}
