package fr.dta.formafond.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authServ;
	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	RestAccessDeniedHandler restAccessDeniedHandler;
	@Autowired
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	@Autowired
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.cors().and()
		.sessionManagement().and().authorizeRequests()
		.and().exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint)
		.accessDeniedHandler(restAccessDeniedHandler)
		.and().formLogin()
		.loginProcessingUrl("/authenticate")
		.successHandler(restAuthenticationSuccessHandler)
		.failureHandler(restAuthenticationFailureHandler)
		.usernameParameter("username")
		.passwordParameter("password")
		.and().logout().logoutUrl("/logout")
		.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
		.permitAll()
		.and()
		.httpBasic()
		.and()
		.csrf()
		.disable();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authServ).passwordEncoder(passwordEncoder());
	}

}
