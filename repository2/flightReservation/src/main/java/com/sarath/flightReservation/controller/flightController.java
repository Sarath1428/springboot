package com.sarath.flightReservation.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarath.flightReservation.entities.Flight;
import com.sarath.flightReservation.repos.flightRepository;

@Controller
public class flightController {

	private static final Logger LOGGER = LoggerFactory.getLogger(flightController.class);
	
	@Autowired
	flightRepository flightRepository;
	
	@RequestMapping(value="/findFlights",method = RequestMethod.POST)
	public String findFlights(@RequestParam("from")String from, @RequestParam("to")String to, 
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy")Date departureDate,ModelMap modelmap) {
		LOGGER.info("inside findFlights()"+ from +"To:"+to + "dapartureDate:" + departureDate);
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelmap.addAttribute("flights", flights);
		LOGGER.info("inside findFlights()"+ flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String addFlight() {
		return "addFlight";
	}
}
