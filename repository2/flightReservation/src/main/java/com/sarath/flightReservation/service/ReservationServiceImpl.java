package com.sarath.flightReservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sarath.flightReservation.dto.ReservationRequest;
import com.sarath.flightReservation.entities.Flight;
import com.sarath.flightReservation.entities.Passenger;
import com.sarath.flightReservation.entities.Reservation;
import com.sarath.flightReservation.repos.flightRepository;
import com.sarath.flightReservation.repos.passengerRepository;
import com.sarath.flightReservation.repos.reservationRepository;
import com.sarath.flightReservation.util.EmailUtil;
import com.sarath.flightReservation.util.PDFGenerator;
@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.sarath.flightReservation.iteninary.dirpath}")
	private  String ITENINERY_DIR ;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private flightRepository flightRepository;
	
	
	@Autowired
	private passengerRepository passengerRepository;
	
	@Autowired
	private reservationRepository reservationRepository;
	
	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Override
	public Reservation BookFlight(ReservationRequest request) {
		
		
		
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching Flight for flight id" +flightId);
		Flight flight = flightRepository.findById(flightId).get();
		
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving the Passenger" +passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving the Reservation" +reservation);
		Reservation savedReservation = reservationRepository.save(reservation);
		System.out.println("saved");
		String filepath1=ITENINERY_DIR+savedReservation.getId()+".pdf";
		LOGGER.info("Generating the Iteninary" );
		pdfGenerator.generateItinerary(savedReservation, filepath1);
		LOGGER.info("Email the Iteninary" );
		emailUtil.sendItinerary(passenger.getEmail(), filepath1);
		
		return savedReservation;
	}

}
