package com.hotelbooking.service;

import com.hotelbooking.dto.BookingRequest;
import com.hotelbooking.entity.Booking;
import com.hotelbooking.entity.Room;
import com.hotelbooking.entity.User;
import com.hotelbooking.exception.BadRequestException;
import com.hotelbooking.exception.ResourceNotFoundException;
import com.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoomService roomService;
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
    }
    
    public List<Booking> getUserBookings(Long userId) {
        User user = userService.getUserById(userId);
        return bookingRepository.findByUserIdOrderByBookingDateDesc(userId);
    }
    
    public List<Booking> getCurrentUserBookings() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new BadRequestException("User not authenticated");
        }
        return bookingRepository.findByUserIdOrderByBookingDateDesc(currentUser.getId());
    }
    
    public List<Booking> getHotelBookings(Long hotelId) {
        return bookingRepository.findByHotelId(hotelId);
    }
    
    public Booking createBooking(BookingRequest bookingRequest) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new BadRequestException("User not authenticated");
        }
        
        Room room = roomService.getRoomById(bookingRequest.getRoomId());
        
        // Validate booking dates
        LocalDate checkInDate = bookingRequest.getCheckInDate();
        LocalDate checkOutDate = bookingRequest.getCheckOutDate();
        
        if (checkInDate.isBefore(LocalDate.now())) {
            throw new BadRequestException("Check-in date cannot be in the past");
        }
        
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new BadRequestException("Check-out date must be after check-in date");
        }
        
        // Check if room is available for the requested dates
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(
                bookingRequest.getRoomId(), checkInDate, checkOutDate);
        
        if (!conflictingBookings.isEmpty()) {
            throw new BadRequestException("Room is not available for the selected dates");
        }
        
        // Check if room can accommodate the number of guests
        if (bookingRequest.getNumberOfGuests() > room.getMaxOccupancy()) {
            throw new BadRequestException("Room cannot accommodate " + bookingRequest.getNumberOfGuests() + " guests");
        }
        
        // Calculate total price
        long numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(numberOfNights));
        
        // Create booking
        Booking booking = new Booking();
        booking.setUser(currentUser);
        booking.setRoom(room);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setNumberOfGuests(bookingRequest.getNumberOfGuests());
        booking.setTotalPrice(totalPrice);
        booking.setSpecialRequests(bookingRequest.getSpecialRequests());
        booking.setBookingStatus(Booking.BookingStatus.PENDING);
        
        return bookingRepository.save(booking);
    }
    
    public Booking updateBookingStatus(Long bookingId, Booking.BookingStatus status) {
        Booking booking = getBookingById(bookingId);
        booking.setBookingStatus(status);
        return bookingRepository.save(booking);
    }
    
    public Booking cancelBooking(Long bookingId) {
        Booking booking = getBookingById(bookingId);
        
        // Check if booking can be cancelled
        if (booking.getBookingStatus() == Booking.BookingStatus.CANCELLED) {
            throw new BadRequestException("Booking is already cancelled");
        }
        
        if (booking.getBookingStatus() == Booking.BookingStatus.COMPLETED) {
            throw new BadRequestException("Cannot cancel a completed booking");
        }
        
        booking.setBookingStatus(Booking.BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
    
    public void deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }
    
    public List<Booking> getBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByDateRange(startDate, endDate);
    }
}
