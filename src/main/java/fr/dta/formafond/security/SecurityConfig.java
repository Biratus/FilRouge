package fr.dta.formafond.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

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
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authServ);
	}

}