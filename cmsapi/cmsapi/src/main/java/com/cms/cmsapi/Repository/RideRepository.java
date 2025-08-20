package com.cms.cmsapi.Repository;

import com.cms.cmsapi.model.RideModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RideRepository extends JpaRepository<RideModel, Long> {
    Optional<RideModel> findByEmail(String email);
    Optional<RideModel> findByPhoneNumber(String phoneNumber);
}
