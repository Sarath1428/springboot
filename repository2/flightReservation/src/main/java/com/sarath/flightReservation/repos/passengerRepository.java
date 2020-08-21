package com.sarath.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightReservation.entities.Passenger;


public interface passengerRepository extends JpaRepository<Passenger, Long> {

}
