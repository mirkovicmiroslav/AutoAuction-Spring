package app.service;

import java.util.List;

import model.Komentar;
import model.Ogla;

public interface IKomentarService {

	public List<Komentar> getKomentariZaOglas(Ogla ooglas);

	public Komentar dodajKomentar(Komentar komentar);
	
	public List<Komentar> getKomentariZaOglasId(Integer idOgla);
}
