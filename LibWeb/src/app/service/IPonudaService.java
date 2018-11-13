package app.service;

import java.util.List;

import model.Ponuda;

public interface IPonudaService {
	
	public List<Ponuda> getAllPonudeByKorisnik(String korisnickoIme);
	
	public Ponuda getTrenutnuPonuduZaOglasId(int oglasId);
	
	public boolean dodajNovuPonudu(Ponuda ponuda);
	
	public List<Ponuda> getAllPonudeByIdOgla(int oglasId);
	
}
