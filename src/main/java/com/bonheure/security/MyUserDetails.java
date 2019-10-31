package com.bonheure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bonheure.domain.Prestataire;
import com.bonheure.domain.User;
import com.bonheure.repository.PrestataireRepository;
import com.bonheure.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrestataireRepository prestataireRepository;

	static boolean isEmail(String email) {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user;
		///final Prestataire prestataire;
		// if type of user = client/admin
		if (isEmail(username)) {

			user = userRepository.findOneByEmail(username).orElse(null);

			if (user == null) {
				throw new UsernameNotFoundException("User with email  '" + username + "' not found");
			}
			
		return org.springframework.security.core.userdetails.User//
					.withUsername(username)//
					.password(user.getPassword())//
					.authorities(user.getRole())//
					.accountExpired(false)//
					.accountLocked(false)//
					.credentialsExpired(false)//
					.disabled(false)//
					.build();

		}
		// else if type of user presataire

	user = userRepository.findOneByMobileNumber(username).orElse(null);

		if (user == null) {
			throw new UsernameNotFoundException("prestataire with mobile number '" + username + "' not found");
		}
		
		
		return org.springframework.security.core.userdetails.User//
				.withUsername(username)//
				.password(user.getPassword())//
				.authorities(user.getRole())//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();

	}
}
