package com.hotelbooking.service;

import com.hotelbooking.dto.HotelSearchRequest;
import com.hotelbooking.entity.Hotel;
import com.hotelbooking.entity.Room;
import com.hotelbooking.exception.ResourceNotFoundException;
import com.hotelbooking.repository.HotelRepository;
import com.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    
    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", id));
    }
    
    public List<Hotel> searchHotels(HotelSearchRequest searchRequest) {
        List<Hotel> hotels;
        
        if (searchRequest.getCity() != null && searchRequest.getCountry() != null) {
            hotels = hotelRepository.findByCityAndCountry(searchRequest.getCity(), searchRequest.getCountry());
        } else if (searchRequest.getCity() != null) {
            hotels = hotelRepository.findByCity(searchRequest.getCity());
        } else if (searchRequest.getCountry() != null) {
            hotels = hotelRepository.findByCountry(searchRequest.getCountry());
        } else if (searchRequest.getName() != null) {
            hotels = hotelRepository.findByNameContaining(searchRequest.getName());
        } else if (searchRequest.getMinRating() != null) {
            hotels = hotelRepository.findByRatingGreaterThanEqual(searchRequest.getMinRating());
        } else {
            hotels = hotelRepository.findAll();
        }
        
        // Filter by rating if specified
        if (searchRequest.getMinRating() != null) {
            hotels = hotels.stream()
                    .filter(hotel -> hotel.getRating() >= searchRequest.getMinRating())
                    .toList();
        }
        
        // Filter hotels that have available rooms for the specified number of guests
        if (searchRequest.getGuests() != null) {
            hotels = hotels.stream()
                    .filter(hotel -> hasAvailableRoomsForGuests(hotel.getId(), searchRequest.getGuests()))
                    .toList();
        }
        
        // Filter hotels with rooms within price range
        if (searchRequest.getMaxPrice() != null) {
            BigDecimal maxPrice = BigDecimal.valueOf(searchRequest.getMaxPrice());
            hotels = hotels.stream()
                    .filter(hotel -> hasRoomsWithinPriceRange(hotel.getId(), maxPrice))
                    .toList();
        }
        
        return hotels;
    }
    
    public List<Room> getHotelRooms(Long hotelId) {
        Hotel hotel = getHotelById(hotelId);
        return roomRepository.findByHotelId(hotelId);
    }
    
    public List<Room> getAvailableRooms(Long hotelId) {
        Hotel hotel = getHotelById(hotelId);
        return roomRepository.findByHotelIdAndIsAvailableTrue(hotelId);
    }
    
    private boolean hasAvailableRoomsForGuests(Long hotelId, Integer guests) {
        List<Room> rooms = roomRepository.findByHotelIdAndMaxOccupancyGreaterThanEqual(hotelId, guests);
        return !rooms.isEmpty();
    }
    
    private boolean hasRoomsWithinPriceRange(Long hotelId, BigDecimal maxPrice) {
        List<Room> rooms = roomRepository.findByHotelIdAndPricePerNightLessThanEqual(hotelId, maxPrice);
        return !rooms.isEmpty();
    }
    
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = getHotelById(id);
        
        hotel.setName(hotelDetails.getName());
        hotel.setDescription(hotelDetails.getDescription());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setCity(hotelDetails.getCity());
        hotel.setCountry(hotelDetails.getCountry());
        hotel.setPhoneNumber(hotelDetails.getPhoneNumber());
        hotel.setEmail(hotelDetails.getEmail());
        hotel.setRating(hotelDetails.getRating());
        hotel.setImageUrl(hotelDetails.getImageUrl());
        hotel.setAmenities(hotelDetails.getAmenities());
        
        return hotelRepository.save(hotel);
    }
    
    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
}
