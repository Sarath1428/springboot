package com.sarath.flightReservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarath.flightReservation.entities.User;
import com.sarath.flightReservation.repos.userRepository;

@Controller
public class userController {
	@Autowired
	private userRepository userRepository;
	
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { 

        return new BCryptPasswordEncoder();

    }
	
	private static final Logger LOGGER = LoggerFactory.getLogger(userController.class);

	@RequestMapping("/")
	public String Homepage() {
		return"index";
	}
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("inside showRegistrationPage()");
		return "login/registerUser";
	}
	@RequestMapping("/login1")
	public String showlogin() {
		LOGGER.info("inside showlogin()");
		return "login/login";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String Registration(@ModelAttribute("user") User user) {
		LOGGER.info("inside Registration()"+ user);
		user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password")String password,ModelMap map) {
		LOGGER.info("inside login()"+email);
		
		User user = userRepository.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return "findFlights";
		}
		else {
			map.addAttribute("msg","Invalid username and password.please try again");
			
		}
		return "login/login";
		
	}

}
