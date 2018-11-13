package app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.IKomentarCrudRepo;
import model.Komentar;
import model.Ogla;

@Service("komentarService")
public class KomentarService implements IKomentarService{

	private IKomentarCrudRepo komentarCrudRepo;
	
	@Override
	public List<Komentar> getKomentariZaOglas(Ogla o) {
		List<Komentar> komentariZaOglas = komentarCrudRepo.getKomentariZaOglasId(o.getIdOgla());
		List<Komentar> parentKomentari = komentariZaOglas.stream()
				.filter(komentar -> komentar.getKomentarRoditeljId() == 0)
				.collect(Collectors.toList());
		for(Komentar k: parentKomentari) {
			komentariZaOglas.stream()
				.filter(komentar -> komentar.getKomentarRoditeljId() ==k.getIdKomentara())
				.collect(Collectors.toList());
		}
		
		return parentKomentari;
	}
	
	@Override
	public List<Komentar> getKomentariZaOglasId(Integer idOgla) {
		return komentarCrudRepo.getKomentariZaOglasId(idOgla);
	}

	@Override
	public Komentar dodajKomentar(Komentar k) {
		return komentarCrudRepo.save(k);
	}
	
	@Autowired
	public void setKomentarRepo(IKomentarCrudRepo komentarCrudRepo) {
		this.komentarCrudRepo = komentarCrudRepo;
	}

	
}
