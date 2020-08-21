package com.sarath.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightReservation.entities.User;

public interface userRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);

}
