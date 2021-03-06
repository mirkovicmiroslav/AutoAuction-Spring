package app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import app.service.KorisnikService;

@Component

public class CustomLoginSuccessfulHandler implements AuthenticationSuccessHandler {

	@Autowired
	private KorisnikService korisnikService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (korisnikService.getKorisnikWithUsername(authentication.getName()).getUloga().getImeUloge().equalsIgnoreCase("USER")) {
			request.getSession().setAttribute("korisnik",
					korisnikService.getKorisnikWithUsername(authentication.getName()));
			response.sendRedirect("/LibWeb/dashboard");
			
		}else if (korisnikService.getKorisnikWithUsername(authentication.getName()).getUloga().getImeUloge().equalsIgnoreCase("ADMIN")) {
			request.getSession().setAttribute("korisnik",
					korisnikService.getKorisnikWithUsername(authentication.getName()));
			response.sendRedirect("/LibWeb/dashboardAdmin");
		}
	}


}