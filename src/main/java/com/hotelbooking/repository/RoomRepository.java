package com.hotelbooking.repository;

import com.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    List<Room> findByHotelId(Long hotelId);
    
    List<Room> findByRoomType(String roomType);
    
    List<Room> findByHotelIdAndRoomType(Long hotelId, String roomType);
    
    List<Room> findByIsAvailableTrue();
    
    List<Room> findByHotelIdAndIsAvailableTrue(Long hotelId);
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.pricePerNight <= :maxPrice")
    List<Room> findByHotelIdAndPricePerNightLessThanEqual(@Param("hotelId") Long hotelId, @Param("maxPrice") BigDecimal maxPrice);
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.maxOccupancy >= :guests")
    List<Room> findByHotelIdAndMaxOccupancyGreaterThanEqual(@Param("hotelId") Long hotelId, @Param("guests") Integer guests);
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.isAvailable = true " +
           "AND r.id NOT IN (SELECT b.room.id FROM Booking b WHERE " +
           "(b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate) " +
           "AND b.bookingStatus IN ('CONFIRMED', 'PENDING'))")
    List<Room> findAvailableRoomsByHotelAndDateRange(@Param("hotelId") Long hotelId, 
                                                     @Param("checkInDate") LocalDate checkInDate, 
                                                     @Param("checkOutDate") LocalDate checkOutDate);
}
