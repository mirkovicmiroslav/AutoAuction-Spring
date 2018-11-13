package app.service;

import model.Korisnik;

public interface IKorisnikService {
	
	public Korisnik getKorisnikWithUsername(String username);

	public Korisnik getKorisnikWithId(int id);
}
