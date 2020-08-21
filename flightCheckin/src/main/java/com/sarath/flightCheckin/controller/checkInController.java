package com.sarath.flightCheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarath.flightCheckin.integration.ReservationRestClient;
import com.sarath.flightCheckin.integration.dto.Reservation;
import com.sarath.flightCheckin.integration.dto.reservationUpdateRequest;

@Controller
public class checkInController 
{
	@Autowired
	ReservationRestClient restclient;

	@RequestMapping("/showcheckin")
	public String ShowCheckin() {
		return "showcheckin";
	}
	
	@RequestMapping("/startcheckin")
	public String startcheckin(@RequestParam("reservationId") Long reservationId, ModelMap map) {
		Reservation reservation = restclient.findReservation(reservationId);
		map.addAttribute("reservation", reservation);
		return "displayReservationDetail";
		
	}
	
	@RequestMapping("/completeCheckin")
	public String completedCheckin(@RequestParam("reservationId") Long reservationId, @RequestParam("numberOfBags")int NumberOfBags,ModelMap map) {
		reservationUpdateRequest request = new reservationUpdateRequest();
		request.setId(reservationId);
		request.setNumberOfBags(NumberOfBags);
		request.setCheckedIn(true);
		Reservation reservation = restclient.updateReservation(request);
		map.addAttribute("msg", reservation);
		return "completedCheckin";
	}
}
