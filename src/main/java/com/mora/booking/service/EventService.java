package com.mora.booking.service;

import com.mora.booking.pojo.Event;
import com.mora.booking.repo.EventRepo;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepo eventRepo;

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    // add new event
    public Event addEvent(Event event) {
        try {
            if (validateEvent(event)) {
                return eventRepo.save(event);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // get event by id
    public Event getEventById(Long eventId) {
        try {
            Event event = eventRepo.getEventByEventId(eventId);
            if (event != null) {
                return event;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // TODO : release tickets of a event
    public void releaseTickets(Long eventId, int totalTickets) {
        Event event = getEventById(eventId);
        event.releaseTickets(totalTickets);
        eventRepo.save(event);
    }

    // TODO:  validate the event
    public boolean validateEvent(Event event) {
        return true;
    }
}
