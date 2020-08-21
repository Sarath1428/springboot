package com.sarath.flightReservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarath.flightReservation.dto.reservationUpdateRequest;
import com.sarath.flightReservation.entities.Reservation;
import com.sarath.flightReservation.repos.reservationRepository;

@RestController
@CrossOrigin
public class reservationRestController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(reservationRestController.class);
	@Autowired
	private reservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id) {
		LOGGER.info("inside findReservation()");
		Reservation reservation = reservationRepository.findById(id).get();
		return reservation;
		
	}
	
	@RequestMapping(value="/reservations")
	@PostMapping
	public Reservation UpdateReservation(@RequestBody reservationUpdateRequest request) {
		LOGGER.info("inside UpdateReservation()"+ request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		LOGGER.info("saving reservation()");
		return reservationRepository.save(reservation);
		
	}
}
