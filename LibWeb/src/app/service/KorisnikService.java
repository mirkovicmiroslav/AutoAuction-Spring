package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.IKorisnikCrudRepo;
import model.Korisnik;

@Service("korisnikService")
public class KorisnikService implements IKorisnikService {
	
    private IKorisnikCrudRepo korisnikService;

    @Override
    public Korisnik getKorisnikWithUsername(String username) {
       return  korisnikService.findByKorisnickoIme(username);
    }

    @Override
    public Korisnik getKorisnikWithId(int id) {
       return korisnikService.findByIdKorisnika(id);
    }
    
    @Autowired
    public void setKorisnikRepo(IKorisnikCrudRepo korisnikService) {
        this.korisnikService = korisnikService;
    }
}