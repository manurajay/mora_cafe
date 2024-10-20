package com.mora.booking.repo;

import com.mora.booking.pojo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface EventRepo extends JpaRepository<Event, Integer> {
    Event getEventByEventId(Long eventId);
}
