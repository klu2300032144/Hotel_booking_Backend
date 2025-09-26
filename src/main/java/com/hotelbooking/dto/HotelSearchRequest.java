package com.hotelbooking.dto;

public class HotelSearchRequest {
    
    private String city;
    private String country;
    private String name;
    private Double minRating;
    private Integer guests;
    private Double maxPrice;
    
    // Constructors
    public HotelSearchRequest() {}
    
    public HotelSearchRequest(String city, String country, String name, Double minRating, Integer guests, Double maxPrice) {
        this.city = city;
        this.country = country;
        this.name = name;
        this.minRating = minRating;
        this.guests = guests;
        this.maxPrice = maxPrice;
    }
    
    // Getters and Setters
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getMinRating() {
        return minRating;
    }
    
    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }
    
    public Integer getGuests() {
        return guests;
    }
    
    public void setGuests(Integer guests) {
        this.guests = guests;
    }
    
    public Double getMaxPrice() {
        return maxPrice;
    }
    
    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
