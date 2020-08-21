package com.sarath.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightReservation.entities.Reservation;


public interface reservationRepository extends JpaRepository<Reservation, Long> {

}
