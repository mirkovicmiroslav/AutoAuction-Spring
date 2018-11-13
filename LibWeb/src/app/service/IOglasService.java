package app.service;

import java.util.List;

import model.Ogla;

public interface IOglasService {
	
	public List<Ogla> sviOglasiByKorisnickoIme(String korisnickoIme);
	
	public List<Ogla> getAllOglasi();

	public List<Ogla> getOglasByFilters(String extensionQuery);
	
	public Ogla dodajOglas(Ogla oglas);

	public Ogla getOglasWithId(int id);

	public void prihvatiPonuduZaOglas(int idOglasa);
}