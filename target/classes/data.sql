-- Sample Hotels Data
INSERT INTO hotels (name, description, address, city, country, phone_number, email, rating, image_url, amenities) VALUES
('Grand Palace Hotel', 'Luxurious hotel in the heart of the city with world-class amenities and exceptional service.', '123 Main Street', 'New York', 'USA', '+1-555-0123', 'info@grandpalace.com', 4.8, 'https://images.unsplash.com/photo-1566073771259-6a8506099945', 'WiFi, Pool, Gym, Spa, Restaurant, Bar, Room Service'),
('Ocean View Resort', 'Beachfront resort with stunning ocean views and tropical atmosphere.', '456 Ocean Drive', 'Miami', 'USA', '+1-555-0456', 'reservations@oceanview.com', 4.6, 'https://images.unsplash.com/photo-1571896349842-33c89424de2d', 'WiFi, Pool, Beach Access, Restaurant, Bar, Spa, Water Sports'),
('Mountain Lodge', 'Cozy lodge nestled in the mountains with breathtaking views and outdoor activities.', '789 Mountain Road', 'Denver', 'USA', '+1-555-0789', 'stay@mountainlodge.com', 4.4, 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4', 'WiFi, Fireplace, Restaurant, Hiking Trails, Skiing, Hot Tub'),
('City Center Inn', 'Modern hotel in the business district with convenient access to corporate offices.', '321 Business Ave', 'Chicago', 'USA', '+1-555-0321', 'business@citycenter.com', 4.2, 'https://images.unsplash.com/photo-1551882547-ff40c63fe5fa', 'WiFi, Business Center, Gym, Restaurant, Conference Rooms'),
('Garden Hotel', 'Charming hotel surrounded by beautiful gardens and peaceful atmosphere.', '654 Garden Lane', 'Portland', 'USA', '+1-555-0654', 'hello@gardenhotel.com', 4.5, 'https://images.unsplash.com/photo-1578662996442-48f60103fc96', 'WiFi, Garden, Restaurant, Spa, Yoga Classes, Bicycle Rental');

-- Sample Rooms Data
INSERT INTO rooms (room_number, room_type, description, price_per_night, max_occupancy, amenities, image_url, is_available, hotel_id) VALUES
-- Grand Palace Hotel Rooms
('101', 'Deluxe Suite', 'Spacious suite with city view, king bed, and luxury amenities', 299.99, 2, 'WiFi, Mini Bar, Room Service, City View', 'https://images.unsplash.com/photo-1631049307264-da0ec9d70304', true, 1),
('102', 'Standard Room', 'Comfortable room with queen bed and modern amenities', 199.99, 2, 'WiFi, TV, Air Conditioning', 'https://images.unsplash.com/photo-1582719478250-c89cae4dc85b', true, 1),
('103', 'Executive Suite', 'Premium suite with separate living area and premium amenities', 399.99, 4, 'WiFi, Mini Bar, Room Service, Separate Living Area', 'https://images.unsplash.com/photo-1618773928121-c32242e63f39', true, 1),

-- Ocean View Resort Rooms
('201', 'Ocean View Room', 'Room with stunning ocean view and balcony', 249.99, 2, 'WiFi, Ocean View, Balcony, Air Conditioning', 'https://images.unsplash.com/photo-1578662996442-48f60103fc96', true, 2),
('202', 'Beachfront Suite', 'Luxury suite with direct beach access', 349.99, 4, 'WiFi, Beach Access, Mini Bar, Room Service', 'https://images.unsplash.com/photo-1582719478250-c89cae4dc85b', true, 2),
('203', 'Standard Room', 'Comfortable room with garden view', 179.99, 2, 'WiFi, Garden View, Air Conditioning', 'https://images.unsplash.com/photo-1631049307264-da0ec9d70304', true, 2),

-- Mountain Lodge Rooms
('301', 'Mountain View Room', 'Cozy room with breathtaking mountain views', 159.99, 2, 'WiFi, Mountain View, Fireplace, Air Conditioning', 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4', true, 3),
('302', 'Family Suite', 'Spacious suite perfect for families', 229.99, 6, 'WiFi, Separate Bedroom, Living Area, Kitchenette', 'https://images.unsplash.com/photo-1618773928121-c32242e63f39', true, 3),
('303', 'Standard Room', 'Comfortable room with forest view', 129.99, 2, 'WiFi, Forest View, Air Conditioning', 'https://images.unsplash.com/photo-1582719478250-c89cae4dc85b', true, 3),

-- City Center Inn Rooms
('401', 'Business Suite', 'Modern suite designed for business travelers', 279.99, 2, 'WiFi, Work Desk, Business Center Access, Room Service', 'https://images.unsplash.com/photo-1631049307264-da0ec9d70304', true, 4),
('402', 'Standard Room', 'Comfortable room with city view', 199.99, 2, 'WiFi, City View, Air Conditioning, Work Desk', 'https://images.unsplash.com/photo-1582719478250-c89cae4dc85b', true, 4),
('403', 'Executive Room', 'Premium room with extra amenities', 329.99, 2, 'WiFi, Premium Amenities, Room Service, City View', 'https://images.unsplash.com/photo-1618773928121-c32242e63f39', true, 4),

-- Garden Hotel Rooms
('501', 'Garden View Room', 'Peaceful room overlooking the hotel gardens', 189.99, 2, 'WiFi, Garden View, Air Conditioning', 'https://images.unsplash.com/photo-1578662996442-48f60103fc96', true, 5),
('502', 'Deluxe Room', 'Spacious room with premium amenities', 239.99, 2, 'WiFi, Premium Amenities, Room Service, Garden View', 'https://images.unsplash.com/photo-1631049307264-da0ec9d70304', true, 5),
('503', 'Standard Room', 'Comfortable room with courtyard view', 149.99, 2, 'WiFi, Courtyard View, Air Conditioning', 'https://images.unsplash.com/photo-1582719478250-c89cae4dc85b', true, 5);

-- Sample Users Data (passwords are encrypted with BCrypt - password is "password123")
INSERT INTO users (username, email, password, first_name, last_name, phone_number, role) VALUES
('admin', 'admin@hotelbooking.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Admin', 'User', '+1-555-0000', 'ADMIN'),
('john_doe', 'john.doe@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'John', 'Doe', '+1-555-0001', 'USER'),
('jane_smith', 'jane.smith@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Jane', 'Smith', '+1-555-0002', 'USER'),
('mike_wilson', 'mike.wilson@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Mike', 'Wilson', '+1-555-0003', 'USER');
