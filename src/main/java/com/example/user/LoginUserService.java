package com.example.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements UserDetailsService {
	private final UserRepository userRepository;

	public LoginUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public LoginUser loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(email).get();

		if (user == null) {
			throw new UsernameNotFoundException("ユーザーが見つかりません");
		}

		return new LoginUser(user);
	}

}
