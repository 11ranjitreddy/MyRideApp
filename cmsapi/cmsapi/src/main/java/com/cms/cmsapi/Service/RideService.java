package com.cms.cmsapi.Service;

import java.util.List;

import com.cms.cmsapi.model.RideModel;
import com.cms.cmsapi.model.LoginRequest;  // Importing the LoginRequest class

public interface RideService {
    RideModel registerRide(RideModel ride);
    String loginRide(LoginRequest loginRequest);  // Updated to use LoginRequest
    RideModel getRideByEmail(String email);
    RideModel getRideByPhoneNumber(String phoneNumber);
    List<RideModel> getAllRides();
    RideModel getRideById(Long id);
}
