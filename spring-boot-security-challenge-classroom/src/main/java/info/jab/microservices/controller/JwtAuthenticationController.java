package info.jab.microservices.controller;

import info.jab.microservices.model.JwtRequest;
import info.jab.microservices.model.JwtResponse;
import info.jab.microservices.model.UserChangePasswordRequest;
import info.jab.microservices.model.UserDetail;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import info.jab.microservices.config.JwtTokenUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import info.jab.microservices.service.impl.JwtUserDetailsServiceImpl;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsServiceImpl jwtInMemoryUserDetailsService;

	@PostMapping(value = "/api/login")
	public ResponseEntity<?> login(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok().body(new JwtResponse(token));
	}

	@PostMapping(value = "/api/users/update-password")
	public ResponseEntity<?> updatePassword(@Valid @RequestBody UserChangePasswordRequest changePasswordRequest) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		authenticate(currentPrincipalName, changePasswordRequest.getCurrentPassword());

		if(changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPassword2())) {

			final UserDetail userDetail = jwtInMemoryUserDetailsService
					.updateUserByUsername(currentPrincipalName, changePasswordRequest.getNewPassword());
			authenticate(currentPrincipalName, changePasswordRequest.getNewPassword());

			return ResponseEntity.ok().body(userDetail);
		}else{
			final UserDetail userDetail = jwtInMemoryUserDetailsService.getUserDetail(currentPrincipalName);

			return ResponseEntity.badRequest().body(userDetail);
		}
	}

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


}
