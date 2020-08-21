package com.sarath.flightReservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarath.flightReservation.dto.ReservationRequest;
import com.sarath.flightReservation.entities.Flight;
import com.sarath.flightReservation.entities.Reservation;
import com.sarath.flightReservation.repos.flightRepository;
import com.sarath.flightReservation.service.ReservationService;
@Controller
public class reservationController 
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(reservationController.class);
	
	@Autowired
	flightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightid,ModelMap map) {
		LOGGER.info("inside showCompleteReservation() invoked with flightId:"+ flightid);
		Flight flight = flightRepository.findById(flightid).get();
		map.addAttribute("flight", flight);
		LOGGER.info("flight is:"+ flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method = RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap map) {
		LOGGER.info("inside completeReservation():"+ request);
		Reservation reservation = reservationService.BookFlight(request);
		map.addAttribute("msg", "Reservation created Successfully with id is " +reservation.getId());
		return "reservationConfirmation";
		
	}

}
