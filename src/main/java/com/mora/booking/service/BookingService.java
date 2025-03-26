package com.mora.booking.service;

import com.mora.booking.pojo.Admin;
import com.mora.booking.pojo.Booking;
import com.mora.booking.pojo.Event;
import com.mora.booking.pojo.common.BookingStatus;
import com.mora.booking.repo.AdminRepo;
import com.mora.booking.repo.BookingRepo;
import com.mora.booking.repo.ConsumerRepo;
import com.mora.booking.repo.EventRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private BookingRepo bookingRepo;

    private EventRepo eventRepo;

    private AdminRepo adminRepo;

    private ConsumerRepo consumerRepo;

    public BookingService(BookingRepo bookingRepo, EventRepo eventRepo, AdminRepo adminRepo, ConsumerRepo consumerRepo) {
        this.bookingRepo = bookingRepo;
        this.eventRepo = eventRepo;
        this.adminRepo = adminRepo;
        this.consumerRepo = consumerRepo;
    }

    // add new booking
    public Booking addBooking(Long eventId, Long userId, int numberOfTickets) {
        Event event = eventRepo.getEventByEventId(eventId);
        Admin admin = adminRepo.findAdminById(userId);

        if (event == null || admin == null) {
            System.out.println("Event not found.");
        }

        if (event.getRemainingTickets() < numberOfTickets) {
            throw new RuntimeException("Not enough tickets available");
        }

        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setUser(admin);
        booking.setNumberOfTickets(numberOfTickets);
        booking.setDate(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);

        event.reduceRemainingTickets(numberOfTickets);
        eventRepo.save(event);

        return bookingRepo.save(booking);
    }

    // get booking by id
    public Booking getBookingById(int bookingId) {
        return bookingRepo.findBookingByBookingId(bookingId);
    }

    // remove booking by id
    public void removeBooking(int bookingId) {
        Booking booking = bookingRepo.findBookingByBookingId(bookingId);
        Event event = booking.getEvent();
        event.releaseTickets(booking.getNumberOfTickets());
        bookingRepo.delete(booking);
        eventRepo.save(event);
    }

}
