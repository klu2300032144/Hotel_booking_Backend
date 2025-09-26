package com.hotelbooking.controller;

import com.hotelbooking.entity.Room;
import com.hotelbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }
    
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable Long hotelId) {
        List<Room> rooms = roomService.getRoomsByHotel(hotelId);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/hotel/{hotelId}/available")
    public ResponseEntity<List<Room>> getAvailableRooms(@PathVariable Long hotelId) {
        List<Room> rooms = roomService.getAvailableRooms(hotelId);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/hotel/{hotelId}/available/dates")
    public ResponseEntity<List<Room>> getAvailableRoomsByDateRange(
            @PathVariable Long hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
        List<Room> rooms = roomService.getAvailableRoomsByDateRange(hotelId, checkInDate, checkOutDate);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByType(@PathVariable String roomType) {
        List<Room> rooms = roomService.getRoomsByType(roomType);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/hotel/{hotelId}/type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByHotelAndType(@PathVariable Long hotelId, @PathVariable String roomType) {
        List<Room> rooms = roomService.getRoomsByHotelAndType(hotelId, roomType);
        return ResponseEntity.ok(rooms);
    }
    
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.ok(createdRoom);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room updatedRoom = roomService.updateRoom(id, roomDetails);
        return ResponseEntity.ok(updatedRoom);
    }
    
    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateRoomAvailability(@PathVariable Long id, @RequestParam boolean isAvailable) {
        roomService.updateRoomAvailability(id, isAvailable);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
