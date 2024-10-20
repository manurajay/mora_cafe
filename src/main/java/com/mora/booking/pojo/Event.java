package com.mora.booking.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private int eventId;

    @NotBlank
    @Column(name = "eventName")
    private String eventName;

    @NotBlank
    @Column(name = "eventDate")
    private LocalDateTime eventDate;

    @NotBlank
    @Column(name = "totalCapacity")
    private int totalCapacity;

    @NotBlank
    @Column(name = "remainingTickets")
    private int remainingTickets;

    @OneToMany(mappedBy = "event")
    private List<Booking> bookings;

    private final ReentrantLock lock = new ReentrantLock();

    public Event(int eventId, String eventName, LocalDateTime eventDate, int totalCapacity) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.totalCapacity = totalCapacity;
    }

    public Event() {

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }

    public void setRemainingTickets(int remainingTickets) {
        this.remainingTickets = remainingTickets;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", totalCapacity=" + totalCapacity +
                ", remainingTickets=" + remainingTickets +
                '}';
    }

    // add tickets ( vendor )
    public void addTicket() {
        lock.lock();
        try {
            if (remainingTickets < totalCapacity) {
                remainingTickets++;
            }
        } finally {
            lock.unlock();
        }
    }

    // book tickets ( consumer )
    public boolean bookTicket() {
        lock.lock();
        try {
            if (remainingTickets > 0) {
                remainingTickets--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void releaseTickets(int numberOfTickets) {
        this.remainingTickets += numberOfTickets;
    }

    public void reduceRemainingTickets(int numberOfTickets) {
        this.remainingTickets -= numberOfTickets;
    }
}
