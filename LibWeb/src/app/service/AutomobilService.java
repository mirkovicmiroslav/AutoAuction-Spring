package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.repository.IAutomobilCrudRepo;
import model.Automobil;

@Service("automobilService")
public class AutomobilService implements IAutomobilService {
	
	private IAutomobilCrudRepo automobilCrudRepo;
	
	@Transactional
	@Override
	public Automobil dodajNoviAutomobil(Automobil automobil) {
		Automobil noviAutomobil = automobilCrudRepo.save(automobil);
		
		return noviAutomobil;
	}

	@Override
	public Automobil getAutomobilByIdOglasa(int idOglasa) {
		Automobil automobil = automobilCrudRepo.findByIdOglasa(idOglasa);
		return automobil;
	}
	
	@Autowired
	public void setnCR(IAutomobilCrudRepo automobilCrudRepo) {
		this.automobilCrudRepo = automobilCrudRepo;
	}

}