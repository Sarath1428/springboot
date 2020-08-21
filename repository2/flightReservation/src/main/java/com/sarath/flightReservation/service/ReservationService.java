package com.sarath.flightReservation.service;

import com.sarath.flightReservation.dto.ReservationRequest;
import com.sarath.flightReservation.entities.Reservation;

public interface ReservationService {

	public Reservation BookFlight(ReservationRequest request);
}
