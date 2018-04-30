package fr.dta.formafond.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	public RestAuthenticationEntryPoint() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
		System.out.println("entry point");
			response.setStatus(HttpServletResponse.SC_OK);
		
	}

}
