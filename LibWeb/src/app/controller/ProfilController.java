package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.service.IOglasService;
import app.service.IPonudaService;
import model.Korisnik;
import model.Ogla;
import model.Ponuda;

@Controller
public class ProfilController {
	
	@Autowired
	private IOglasService oglasService;
	@Autowired
	private IPonudaService ponudaService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String getAllKorisniciById(Model m, HttpServletRequest request, HttpSession session) {
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		if (korisnik.getUloga().getIdUloge()!=1) {
			return "login";
		}
		
		List<Ponuda> ponude = ponudaService.getAllPonudeByKorisnik(korisnik.getKorisnickoIme());
		m.addAttribute("ponude", ponude);
		
		List<Ogla> oglasi = oglasService.sviOglasiByKorisnickoIme(korisnik.getKorisnickoIme());
		m.addAttribute("oglasi", oglasi);
		
		/**List<Ponuda> p = pCR.getAllPonude(korisnik.getKorisnickoIme());
		if(p.size()!=0) {
		p.stream().max(Comparator.comparing(Ponuda::getIdPonude)).get();
		
			m.addAttribute("prihvaceniOglasi", p);
		}**/
		
		return "korisnik/index";
	}
	
}