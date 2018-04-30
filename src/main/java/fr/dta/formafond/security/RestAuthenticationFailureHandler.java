package fr.dta.formafond.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler{

	public RestAuthenticationFailureHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
		System.out.println("access failure");
		response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		
	}

}
