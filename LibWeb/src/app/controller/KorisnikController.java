package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.repository.IKorisnikCrudRepo;
import app.service.IKorisnikService;
import model.Korisnik;

@Controller
public class KorisnikController {

    private IKorisnikService korisnikService;
    @Autowired
    private IKorisnikCrudRepo kCR;

    @RequestMapping(value = "/korisnikSlika/{username}")
    @ResponseBody
    public byte[] getImageKorisnika(@PathVariable String username)  {

        Korisnik korisnik = korisnikService.getKorisnikWithUsername(username);

        return korisnik.getSlika();
    }
    
    @RequestMapping(value="/profil", method = RequestMethod.GET)
    public String getProfil(Model m,HttpSession session){
    	Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
    	
    	Korisnik k = kCR.findByKorisnickoIme(korisnik.getKorisnickoIme());
    	
    	m.addAttribute("korisnik", k);
        return "korisnik/profil";
    }

    @Autowired
    public void setKorisnikService(IKorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }
}
