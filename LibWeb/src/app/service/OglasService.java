package app.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.repository.IOglasCrudRepo;
import app.repository.OglasFilterFindRepo;
import model.Ogla;

@Service("oglasService")
public class OglasService implements IOglasService {

	private IOglasCrudRepo oglasRepo;
	private OglasFilterFindRepo oglasFilterRepo;

	@Override
	public List<Ogla> sviOglasiByKorisnickoIme(String korisnickoIme) {
		List<Ogla> oglasi = oglasRepo.sviOglasiByKorisnickoIme(korisnickoIme);
		List<Ogla> noviOglas = oglasi.stream().sorted(Comparator.comparing(Ogla::getAktivan).thenComparing(Ogla::getIdOgla).reversed())
				.collect(Collectors.toList());

		return noviOglas;
	}

	@Override
	public List<Ogla> getOglasByFilters(String extensionQuery) {
		
		return oglasFilterRepo.getOglasiBy(extensionQuery);
	}

	@Override
	public List<Ogla> getAllOglasi() {
		List<Ogla> oglasi = oglasRepo.findSviAktivniOglasi();
		List<Ogla> sortiraniOglasi = oglasi.stream().sorted(Comparator.comparing(Ogla::getIdOgla).reversed()).collect(Collectors.toList());
		
		return sortiraniOglasi;
	}
	
	@Transactional
	@Override
	public Ogla dodajOglas(Ogla oglas) {
		Ogla noviOglas = oglasRepo.save(oglas);
		
		return noviOglas;
	}

	@Override
	public void prihvatiPonuduZaOglas(int id_oglas){
		Ogla ogla = oglasRepo.findByIdOgla(id_oglas);
		ogla.setAktivan(0);
		oglasRepo.save(ogla);
		System.out.println("SACUVAN OGLAS !!!!!!!!!");
	}

	@Override
	public Ogla getOglasWithId(int id) {
		
		return oglasRepo.findByIdOgla(id);
	}

	@Autowired
	public void setoJP(IOglasCrudRepo oglasRepo) {
		this.oglasRepo = oglasRepo;
	}
	
	@Autowired
	public void setOglasFilterRepo(OglasFilterFindRepo oglasFilterRepo) {
		this.oglasFilterRepo = oglasFilterRepo;
	}

}