package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.service.UserSecurityService;

@Configuration
@EnableWebSecurity
@ComponentScan("app")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userSecurityService")
	private UserSecurityService userSecurityService;

	@Autowired
	private CustomLoginSuccessfulHandler loginSuccessfulHandler;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/register").permitAll();
    	
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll();
    	
    	http.authorizeRequests().antMatchers("/","/profil","/dashboard","/dashboardAdmin","/oglas/dodajPonudu","/oglas/dodajNovi", "/oglas/dodajKomentar").authenticated();

    	http.authorizeRequests().antMatchers("/oglas/svi", "/oglas/rezultatiPretrage","/oglas/detaljiOglasa").permitAll();

    	http.authorizeRequests().antMatchers(HttpMethod.POST,"/profil","/dashboardAdmin","/korisnik/**","/oglas/**").access("hasRole('ADMIN')")
    							.antMatchers(HttpMethod.POST,"/dashboard","/profil","/korisnik/**","/oglas/**").access("hasRole('USER')")
    			
    				.and().formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .usernameParameter("korisnickoIme")
                    .passwordParameter("lozinka")
                    .successHandler(loginSuccessfulHandler)

                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll();

    	http.csrf().disable();

    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void setUserService(UserSecurityService userSecurityService) {
		this.userSecurityService = userSecurityService;
	}

}