package com.mora.booking.repo;

import com.mora.booking.pojo.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

    Booking findBookingByBookingId(Integer bookingId);
}
