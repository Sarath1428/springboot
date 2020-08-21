package com.sarath.flightCheckin.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sarath.flightCheckin.integration.dto.Reservation;
import com.sarath.flightCheckin.integration.dto.reservationUpdateRequest;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	@Value("${com.sarath.flightCheckin.hostname}")
	private String FLIGHT_RESERVATIONS_Url;

	@Override
	public Reservation findReservation(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.getForObject(FLIGHT_RESERVATIONS_Url + id, Reservation.class);
		return reservation;
	}

	@Override
	public Reservation updateReservation(reservationUpdateRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.postForObject(FLIGHT_RESERVATIONS_Url, request, Reservation.class);
		return reservation;
	}

}
