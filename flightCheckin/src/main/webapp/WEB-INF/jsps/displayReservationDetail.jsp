<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation Details</title>
</head>
<body>
<h2>Flight Details</h2>
Airlines: ${reservation.flight.operatingAirlines}<br>
Departure City: ${reservation.flight.departureCity}<br>
Arrival City: ${reservation.flight.arrivalCity}<br>
Date of Arrival: ${reservation.flight.dateOfDeparture}<br>

<h2>Passenger Details</h2>

First Name: ${reservation.passenger.firstName}<br>
Last Name: ${reservation.passenger.lastName}<br>
Email: ${reservation.passenger.email}<br>
Phone: ${reservation.passenger.phone}<br>

<form action="completeCheckin" method="post">
Enter the number of bags: <input type="text" name="numberOfBags"><br>
<input type="hidden" value="${reservation.id}" name="reservationId">
<input type="submit" value="checkIn">

</form>

</body>
</html>