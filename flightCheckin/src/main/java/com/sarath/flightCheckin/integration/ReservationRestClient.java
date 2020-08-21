package com.sarath.flightCheckin.integration;

import com.sarath.flightCheckin.integration.dto.Reservation;
import com.sarath.flightCheckin.integration.dto.reservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	public Reservation updateReservation(reservationUpdateRequest request);
}
