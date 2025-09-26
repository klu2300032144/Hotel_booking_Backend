package com.hotelbooking.service;

import com.hotelbooking.entity.Hotel;
import com.hotelbooking.entity.Room;
import com.hotelbooking.exception.ResourceNotFoundException;
import com.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private HotelService hotelService;
    
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }
    
    public List<Room> getRoomsByHotel(Long hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return roomRepository.findByHotelId(hotelId);
    }
    
    public List<Room> getAvailableRooms(Long hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return roomRepository.findByHotelIdAndIsAvailableTrue(hotelId);
    }
    
    public List<Room> getAvailableRoomsByDateRange(Long hotelId, LocalDate checkInDate, LocalDate checkOutDate) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return roomRepository.findAvailableRoomsByHotelAndDateRange(hotelId, checkInDate, checkOutDate);
    }
    
    public List<Room> getRoomsByType(String roomType) {
        return roomRepository.findByRoomType(roomType);
    }
    
    public List<Room> getRoomsByHotelAndType(Long hotelId, String roomType) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return roomRepository.findByHotelIdAndRoomType(hotelId, roomType);
    }
    
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
    
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = getRoomById(id);
        
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setRoomType(roomDetails.getRoomType());
        room.setDescription(roomDetails.getDescription());
        room.setPricePerNight(roomDetails.getPricePerNight());
        room.setMaxOccupancy(roomDetails.getMaxOccupancy());
        room.setAmenities(roomDetails.getAmenities());
        room.setImageUrl(roomDetails.getImageUrl());
        room.setIsAvailable(roomDetails.getIsAvailable());
        
        return roomRepository.save(room);
    }
    
    public void deleteRoom(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
    
    public void updateRoomAvailability(Long roomId, boolean isAvailable) {
        Room room = getRoomById(roomId);
        room.setIsAvailable(isAvailable);
        roomRepository.save(room);
    }
}
