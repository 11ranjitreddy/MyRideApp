package com.cms.cmsapi.Service;

import com.cms.cmsapi.model.LoginRequest;
import com.cms.cmsapi.model.RideModel;
import com.cms.cmsapi.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    // Register ride
    @Override
    public RideModel registerRide(RideModel ride) {
        if (!ride.isAgreedToTerms()) {
            throw new RuntimeException("You must agree to the terms.");
        }

        String email = ride.getEmail().trim().toLowerCase();
        String phoneNumber = ride.getPhoneNumber().trim();

        // Check if email or phone number exists
        if (rideRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        if (rideRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new RuntimeException("Phone number already exists.");
        }

        ride.setEmail(email);
        ride.setPhoneNumber(phoneNumber);
        ride.setPassword(ride.getPassword().trim());

        return rideRepository.save(ride);
    }

    // Login ride using LoginRequest DTO
    @Override
    public String loginRide(LoginRequest loginRequest) {
        String cleanEmail = loginRequest.getEmail().trim().toLowerCase();
        String cleanPassword = loginRequest.getPassword().trim();

        Optional<RideModel> ride = rideRepository.findByEmail(cleanEmail);

        if (ride.isPresent()) {
            String storedPassword = ride.get().getPassword().trim();
            
            // Check for any hidden characters in the passwords
            System.out.println("Stored Password Length: " + storedPassword.length());
            System.out.println("Provided Password Length: " + cleanPassword.length());
            System.out.println("Stored Password: [" + storedPassword + "]");
            System.out.println("Provided Password: [" + cleanPassword + "]");

            if (storedPassword.equals(cleanPassword)) {
                return "Login successful";
            }
        }

        throw new RuntimeException("Invalid email or password");
    }

    // Get ride by email
    @Override
    public RideModel getRideByEmail(String email) {
        return rideRepository.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new RuntimeException("Ride not found with email: " + email));
    }

    // Get ride by phone number
    @Override
    public RideModel getRideByPhoneNumber(String phoneNumber) {
        return rideRepository.findByPhoneNumber(phoneNumber.trim())
                .orElseThrow(() -> new RuntimeException("Ride not found with phone: " + phoneNumber));
    }

    // Get all rides
    @Override
    public List<RideModel> getAllRides() {
        return rideRepository.findAll();
    }

    // Get ride by ID
    @Override
    public RideModel getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
    }
}
