package com.hotelbooking.controller;

import com.hotelbooking.dto.HotelSearchRequest;
import com.hotelbooking.entity.Hotel;
import com.hotelbooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:5173")
public class HotelController {
    
    @Autowired
    private HotelService hotelService;
    
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }
    
    @PostMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(@RequestBody HotelSearchRequest searchRequest) {
        List<Hotel> hotels = hotelService.searchHotels(searchRequest);
        return ResponseEntity.ok(hotels);
    }
    
    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<com.hotelbooking.entity.Room>> getHotelRooms(@PathVariable Long id) {
        List<com.hotelbooking.entity.Room> rooms = hotelService.getHotelRooms(id);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/{id}/rooms/available")
    public ResponseEntity<List<com.hotelbooking.entity.Room>> getAvailableRooms(@PathVariable Long id) {
        List<com.hotelbooking.entity.Room> rooms = hotelService.getAvailableRooms(id);
        return ResponseEntity.ok(rooms);
    }
    
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.ok(createdHotel);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotelDetails);
        return ResponseEntity.ok(updatedHotel);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
