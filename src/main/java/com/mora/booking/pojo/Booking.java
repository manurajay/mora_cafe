package com.mora.booking.pojo;

import com.mora.booking.pojo.common.BookingStatus;
import com.mora.cafe.pojo.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

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
    @Column(name = "numberOfTickets", nullable = false)
    private int numberOfTickets;

    @NotBlank
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @NotBlank
    @Column(name = "bookingStatus")
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Admin user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Booking(int bookingId, int numberOfTickets, LocalDateTime date, com.mora.booking.pojo.common.BookingStatus bookingStatus, Admin user, Event event) {
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

    public com.mora.booking.pojo.common.BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(com.mora.booking.pojo.common.BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(Admin user) {
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
