package com.mora.booking.rest;

import com.mora.booking.pojo.Booking;
import com.mora.booking.service.BookingService;
import com.mora.booking.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")
public class BookingController {

    private final BookingService  bookingService;
    private final TicketService ticketService;

    public BookingController(BookingService bookingService, TicketService ticketService) {
        this.bookingService = bookingService;
        this.ticketService = ticketService;
    }

    // create booking for an event
    @PostMapping("/")
    public ResponseEntity<String> createBooking(
            @RequestParam Long eventId,
            @RequestParam Long userId,
            @RequestParam int numberOfTickets) {
        // Call service to create the booking
        bookingService.addBooking(eventId, userId, numberOfTickets);
        ticketService.startConsumer(numberOfTickets);
        return ResponseEntity.ok("Booking created successfully.");
    }

    // get booking by id
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    // remove a booking by id
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> removeBooking(@PathVariable int bookingId) {
        bookingService.removeBooking(bookingId);
        return ResponseEntity.ok("Booking canceled successfully.");
    }
}
