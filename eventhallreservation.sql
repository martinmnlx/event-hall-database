-- Event Hall Reservation System Database Schema
-- CCINFOM-S27-09 | Group 6
-- Members: Ancheta, Bongco, Macatangay, Manalo
-- Date: November 2025

CREATE DATABASE event_hall_reservation;
USE event_hall_reservation;

-- ===============================
-- USERS TABLE
-- This is the table for the users.
-- ===============================
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('Customer', 'Admin', 'Staff') NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    password_hash VARCHAR(255) NOT NULL,
    date_created DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- EVENT HALLS TABLE
-- This is the table for the event halls.
-- ===============================
CREATE TABLE Event_Halls (
    hall_id INT AUTO_INCREMENT PRIMARY KEY,
    hall_name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    location VARCHAR(255),
    rate_per_hour DECIMAL(10,2) NOT NULL,
    status ENUM('Available', 'Booked', 'Under Maintenance') DEFAULT 'Available'
);

-- ===============================
-- STAFF TABLE
-- This is the table for the staff.
-- ===============================
CREATE TABLE Staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50),
    department VARCHAR(100),
    contact_number VARCHAR(20),
    shift_schedule VARCHAR(100)
);

-- ===============================
-- EQUIPMENT TABLE
-- This is the table for the equipments
-- ===============================
CREATE TABLE Equipment (
    equipment_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_name VARCHAR(100) NOT NULL,
    total_quantity INT NOT NULL,
    rental_rate DECIMAL(10,2),
    status ENUM('Available', 'In Use', 'Maintenance') DEFAULT 'Available'
);

-- ===============================
-- RESERVATIONS TABLE
-- This is the table for the reservations.
-- ===============================
CREATE TABLE Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    hall_id INT NOT NULL,
    event_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    event_type VARCHAR(100),
    guest_count INT,
    status ENUM('Pending', 'Confirmed', 'Canceled', 'Completed') DEFAULT 'Pending',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (hall_id) REFERENCES Event_Halls(hall_id)
);

-- ===============================
-- EQUIPMENT ALLOCATIONS TABLE
-- This is the table for the equipment allocations.
-- ===============================
CREATE TABLE Equipment_Allocations (
    alloc_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL,
    equipment_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)
        ON DELETE CASCADE,
    FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id)
        ON DELETE CASCADE
);

-- ===============================
-- PAYMENTS TABLE
-- This is the table for the payments.
-- ===============================
CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    method ENUM('Cash', 'Credit Card', 'Bank Transfer', 'GCash') NOT NULL,
    payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Paid', 'Refunded') DEFAULT 'Pending',
    FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)
        ON DELETE CASCADE
);

-- ===============================
-- STAFF ASSIGNMENTS
-- If you want to track which staff is assigned to which hall or reservation
-- ===============================
CREATE TABLE Staff_Assignments (
    staff_id INT NOT NULL,
    hall_id INT,
    reservation_id INT,
    role_description VARCHAR(100),
    PRIMARY KEY (staff_id, reservation_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id),
    FOREIGN KEY (hall_id) REFERENCES Event_Halls(hall_id),
    FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)
);

-- ===============================
-- SAMPLE DATA INSERTION 
-- Can be updated and manipulates as project develops
-- ===============================

INSERT INTO Users (type, name, email, phone, password_hash)
VALUES
('Customer', 'Juan Dela Cruz', 'juan@email.com', '09171234567', 'hashed_password'),
('Admin', 'Maria Santos', 'maria@email.com', '09981234567', 'hashed_password');

INSERT INTO Event_Halls (hall_name, capacity, location, rate_per_hour, status)
VALUES
('Emerald Hall', 200, '2nd Floor, Building A', 5000.00, 'Available'),
('Ruby Hall', 100, '3rd Floor, Building B', 3000.00, 'Available');

INSERT INTO Equipment (equipment_name, total_quantity, rental_rate)
VALUES
('Projector', 10, 800.00),
('Sound System', 5, 1500.00),
('Table', 50, 100.00),
('Chair', 200, 50.00);

