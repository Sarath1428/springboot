package com.sarath.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightReservation.entities.Role;

public interface roleRepository extends JpaRepository<Role, Long> {

}
