package com.hotelbooking.repository;

import com.hotelbooking.entity.Booking;
import com.hotelbooking.entity.Booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByUserId(Long userId);
    
    List<Booking> findByRoomId(Long roomId);
    
    List<Booking> findByBookingStatus(BookingStatus status);
    
    List<Booking> findByUserIdAndBookingStatus(Long userId, BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId ORDER BY b.bookingDate DESC")
    List<Booking> findByUserIdOrderByBookingDateDesc(@Param("userId") Long userId);
    
    @Query("SELECT b FROM Booking b WHERE b.room.hotel.id = :hotelId")
    List<Booking> findByHotelId(@Param("hotelId") Long hotelId);
    
    @Query("SELECT b FROM Booking b WHERE b.checkInDate >= :startDate AND b.checkOutDate <= :endDate")
    List<Booking> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
           "((b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate) " +
           "AND b.bookingStatus IN ('CONFIRMED', 'PENDING'))")
    List<Booking> findConflictingBookings(@Param("roomId") Long roomId, 
                                         @Param("checkInDate") LocalDate checkInDate, 
                                         @Param("checkOutDate") LocalDate checkOutDate);
}
