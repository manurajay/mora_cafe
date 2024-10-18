package com.mora.booking.models;

import com.mora.booking.models.common.bookingStatus;
import com.mora.cafe.POJO.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private int bookingId;

    @NotBlank
    @Column(name = "numberOfTickets")
    private int numberOfTickets;

    @NotBlank
    @Column(name = "date")
    private LocalDateTime date;

    @NotBlank
    @Column(name = "bookingStatus")
    private bookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Booking(int bookingId, int numberOfTickets, LocalDateTime date, com.mora.booking.models.common.bookingStatus bookingStatus, User user, Event event) {
        this.bookingId = bookingId;
        this.numberOfTickets = numberOfTickets;
        this.date = date;
        this.bookingStatus = bookingStatus;
        this.user = user;
        this.event = event;
    }

    public Booking() {

    }


    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public com.mora.booking.models.common.bookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(com.mora.booking.models.common.bookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", numberOfTickets=" + numberOfTickets +
                ", date=" + date +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}
