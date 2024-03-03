package com.example.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class) // トランザクション管理を行い、登録に失敗したときはロールバックを行う
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;

	public UserService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}


	/*
	 * メールアドレスが既に登録されているか確認する
	 */
	public boolean emailDuplicate(String email) {
		return this.userRepository.findByEmail(email).isPresent();
	}

	/*
	 * ユーザー情報　新規登録
	 * @param user ユーザー情報
	 */
	public void create(UserRequest userRequest, HttpServletRequest request) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		this.userRepository.save(user);

		// ユーザー登録後の自動ログイン
		autoLogin(request, userRequest.getEmail(), userRequest.getPassword());
	}

	// ユーザー登録後の自動ログインを行うためのメソッド
	private void autoLogin(HttpServletRequest request, String email, String password) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
		authToken.setDetails(new WebAuthenticationDetails(request));

		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}

