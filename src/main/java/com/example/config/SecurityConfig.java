package com.example.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	// システム全体へのセキュリティ設定を行う
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	// 特定のHTTPリクエストに応じた認証・認可を行う 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//              .csrf().disable() // CSRF対策を無効にする
				.authorizeRequests()
				.antMatchers("/loginForm", "/registration").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginProcessingUrl("/login") //ログイン処理のパス
				.loginPage("/loginForm")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/category/list", true)
				.failureUrl("/loginForm?error")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/loginForm");
	}

	// パスワードのハッシュ化を行う
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 新規登録後自動ログインを行う
	public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
		try {
			request.login(username, password);
		} catch (ServletException e) {
			LOGGER.error("Error while login ", e);
		}
	}
}