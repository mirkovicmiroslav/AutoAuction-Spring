package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.repository.IPonudaCrudRepo;
import model.Ogla;
import model.Ponuda;

@Service("ponudaService")
public class PonudaService implements IPonudaService{

	private IPonudaCrudRepo ponudaCrudRepo;
	
	@Override
	public List<Ponuda> getAllPonudeByKorisnik(String korisnickoIme) {
		List<Ponuda> list = ponudaCrudRepo.getAllPonudeByKorisnik(korisnickoIme);
		return list;
	}

	@Override
	public Ponuda getTrenutnuPonuduZaOglasId(int oglasId) {
		List<Ponuda> p = ponudaCrudRepo.getPonudeZaOglasId(oglasId);

		if(p.size() >= 1)
			return p.get(p.size() - 1);

		return null;
	}
	
	@Override
	public List<Ponuda> getAllPonudeByIdOgla(int oglasId) {
		List<Ponuda> ponude = ponudaCrudRepo.getAllPonudeByIdOgla(oglasId);
		
		return ponude;
	}
	
	@Transactional
	@Override
	public boolean dodajNovuPonudu(Ponuda ponuda){
		List<Ponuda> prethodnaL = ponudaCrudRepo.getPonudeZaOglasId(ponuda.getOgla().getIdOgla());
		if(prethodnaL.size() >= 1){
			Ponuda prethodna = prethodnaL.get(prethodnaL.size() - 1);
			if(prethodna.getPonudaPare() > ponuda.getPonudaPare())
				return false;
		}
		if(ponuda.getOgla().getMinPonuda() > ponuda.getPonudaPare())
			return false;
		
		LocalDateTime ldt = LocalDateTime.now();
		ponuda.setDatumVreme(java.sql.Timestamp.valueOf(ldt));
		ponudaCrudRepo.save(ponuda);

		return true;
	}
	
	@Autowired
	public void setPonudaCrudRepo(IPonudaCrudRepo ponudaCrudRepo) {
		this.ponudaCrudRepo = ponudaCrudRepo;
	}

}
