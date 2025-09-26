package com.hotelbooking.repository;

import com.hotelbooking.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
    List<Hotel> findByCity(String city);
    
    List<Hotel> findByCountry(String country);
    
    List<Hotel> findByCityAndCountry(String city, String country);
    
    @Query("SELECT h FROM Hotel h WHERE h.name LIKE %:name%")
    List<Hotel> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT h FROM Hotel h WHERE h.rating >= :minRating")
    List<Hotel> findByRatingGreaterThanEqual(@Param("minRating") Double minRating);
    
    @Query("SELECT h FROM Hotel h WHERE h.city = :city AND h.rating >= :minRating")
    List<Hotel> findByCityAndRatingGreaterThanEqual(@Param("city") String city, @Param("minRating") Double minRating);
}
