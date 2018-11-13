package app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.repository.IKorisnikCrudRepo;
import model.Korisnik;

@Service("userSecurityService")
public class UserSecurityService implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private IKorisnikCrudRepo korisnikCrudRepo;

	@Override
	public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikCrudRepo.findByKorisnickoIme(korisnickoIme);
		if (korisnik == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(korisnik.getKorisnickoIme(),
				korisnik.getLozinka(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public Korisnik saveNewKorisnik(Korisnik korisnik) {

		Korisnik rez = korisnikCrudRepo.findByKorisnickoIme(korisnik.getKorisnickoIme());
		if (rez != null)
			return null;

		korisnik.setLozinka(bCryptPasswordEncoder.encode(korisnik.getLozinka()));

		return korisnikCrudRepo.save(korisnik);
	}

}