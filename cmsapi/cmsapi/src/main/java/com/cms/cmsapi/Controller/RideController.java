package com.cms.cmsapi.Controller;

import com.cms.cmsapi.Service.RideServiceImpl;
import com.cms.cmsapi.model.LoginRequest;
import com.cms.cmsapi.model.RideModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*") // Allows frontend to access this controller
public class RideController {

    @Autowired
    private RideServiceImpl rideService;

    // POST - Register Ride
    @PostMapping("/register")
    public ResponseEntity<RideModel> registerRide(@RequestBody RideModel ride) {
        RideModel registeredRide = rideService.registerRide(ride);
        return new ResponseEntity<>(registeredRide, HttpStatus.CREATED);
    }

    // POST - Login Ride
    @PostMapping("/login")
    public ResponseEntity<?> loginRide(@RequestBody LoginRequest loginRequest) {
        try {
            String loginMessage = rideService.loginRide(loginRequest);

            // Simulate token for now (you can replace with JWT later)
            String fakeToken = "dummy-token-123";

            Map<String, String> response = new HashMap<>();
            response.put("message", loginMessage);
            response.put("token", fakeToken);
            return ResponseEntity.ok(response);


        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("message", e.getMessage()));
        }
    }


    // GET by Email
    @GetMapping("/email/{email}")
    public RideModel getRideByEmail(@PathVariable String email) {
        return rideService.getRideByEmail(email);
    }

    // GET by Phone Number
    @GetMapping("/phone/{phoneNumber}")
    public RideModel getRideByPhoneNumber(@PathVariable String phoneNumber) {
        return rideService.getRideByPhoneNumber(phoneNumber);
    }

    // GET by ID
    @GetMapping("/{id}")
    public RideModel getRideById(@PathVariable Long id) {
        return rideService.getRideById(id);
    }

    // GET all rides
    @GetMapping
    public List<RideModel> getAllRides() {
        return rideService.getAllRides();
    }
}
