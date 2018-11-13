package app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import app.repository.IAutomobilCrudRepo;
import app.repository.IKomentarCrudRepo;
import app.repository.IOglasCrudRepo;
import app.repository.IPonudaCrudRepo;
import app.service.IAutomobilService;
import app.service.IBrendService;
import app.service.IKomentarService;
import app.service.IOglasService;
import app.service.IPonudaService;
import model.Automobil;
import model.Brend;
import model.Komentar;
import model.Korisnik;
import model.Ogla;
import model.OglasSearchDto;
import model.Ponuda;

@Controller
@RequestMapping(value = "/oglas")
public class OglasController {

	@Autowired
	private IOglasCrudRepo oCR;
	@Autowired
	private IAutomobilCrudRepo aCR;
	@Autowired
	private IPonudaCrudRepo pCR;
	@Autowired
	private IKomentarCrudRepo kCR;

	private IBrendService brendService;
	private IAutomobilService automobilService;
	private IOglasService oglasService;
	private IPonudaService ponudaService;
	private IKomentarService komentarService;

	@RequestMapping(value = "/dodajNovi", method = RequestMethod.GET)
	public String getAllBrend(Model m, HttpServletRequest request) {
		List<Brend> brendoviAutomobila = brendService.getAllBrendoviAutomobila();
		request.getSession().setAttribute("brendovi", brendoviAutomobila);
		m.addAttribute("brendovi", brendoviAutomobila);

		return "korisnik/dodajOglas";
	}

	/**
	 * @RequestMapping(value= "/dodajNovi", method=RequestMethod.POST) public String
	 *                        dodavanjeOglasa(@Valid @ModelAttribute("oglas") Ogla
	 *                        oglas,BindingResult bindingResult,Automobil a, Model
	 *                        m, @RequestParam("file") MultipartFile file,
	 *                        HttpSession session) throws IOException { Korisnik
	 *                        korisnik = (Korisnik)
	 *                        session.getAttribute("korisnik");
	 * 
	 *                        if(bindingResult.hasErrors()) return
	 *                        "korisnik/dodajOglas";
	 * 
	 *                        if (file != null)
	 *                        a.setSlikaAutomobila(file.getBytes());
	 * 
	 *                        automobilService.dodajNoviAutomobil(a);
	 * 
	 *                        oglas.setAutomobil(a); oglas.setAktivan(1);
	 *                        oglas.setKorisnik(korisnik); LocalDateTime ldt =
	 *                        LocalDateTime.now();
	 *                        oglas.setDatumVreme(java.sql.Timestamp.valueOf(ldt));
	 *                        oglasService.dodajOglas(oglas);
	 * 
	 *                        m.addAttribute("oglas", new Ogla());
	 * 
	 * 
	 *                        return "korisnik/dodajOglas"; }
	 **/

	@PostMapping("/dodajNovi")
	public String dodavanjeOglasa(@Valid Ogla oglas, BindingResult bindingResult, Model m,
			@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request)
			throws IOException {

		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		Automobil a = new Automobil();
		String boja = request.getParameter("boja");
		String model = request.getParameter("model");
		int godiste = Integer.parseInt(request.getParameter("godiste"));
		int kilometraza = Integer.parseInt(request.getParameter("kilometraza"));
		String menjac = request.getParameter("menjac");
		String pogon = request.getParameter("pogon");
		int sedista = Integer.parseInt(request.getParameter("sedista"));
		int snaga = Integer.parseInt(request.getParameter("snaga"));
		String tipGoriva = request.getParameter("tipGoriva");
		int vrata = Integer.parseInt(request.getParameter("vrata"));
		int idBrenda = Integer.parseInt(request.getParameter("brend"));

		String naslov = request.getParameter("naslov");
		String tekst = request.getParameter("tekst");
		double minPonuda = Double.parseDouble(request.getParameter("minPonuda"));

		a.setBoja(boja);
		a.setGodiste(godiste);
		a.setModel(model);
		a.setKilometraza(kilometraza);
		a.setMenjac(menjac);
		a.setPogon(pogon);
		a.setSedista(sedista);
		a.setSnaga(snaga);
		a.setTipGoriva(tipGoriva);
		a.setVrata(vrata);
		Brend b = new Brend();
		b.setIdBrenda(idBrenda);
		a.setBrend(b);

		if (naslov.length() == 0) {
			m.addAttribute("naslovPrazan", "Polje naslov ne smije biti prazno");
		}
		if(model.length()==0) {
			m.addAttribute("modelPrazan", "Polje model ne smije biti prazno");
		}
		if (file != null)
			a.setSlikaAutomobila(file.getBytes());
		else
			a.setSlikaAutomobila(null);

		if (bindingResult.hasErrors()) {
			return "korisnik/dodajOglas";
		}

		automobilService.dodajNoviAutomobil(a);

		oglas.setAutomobil(a);
		oglas.setNaslov(naslov);
		oglas.setTekst(tekst);
		oglas.setMinPonuda(minPonuda);
		oglas.setAktivan(1);
		oglas.setKorisnik(korisnik);
		LocalDateTime ldt = LocalDateTime.now();
		oglas.setDatumVreme(java.sql.Timestamp.valueOf(ldt));
		oglasService.dodajOglas(oglas);

		m.addAttribute("oglas", new Ogla());

		return "korisnik/dodajOglas";
	}

	@RequestMapping(value = "/rezultatiPretrage", method = RequestMethod.POST)
	public String getOglasiBy(@ModelAttribute("oglasSearchDto") OglasSearchDto searchConfiguration, Model model) {
		List<Ogla> oglasi = oglasService.getOglasByFilters(searchConfiguration.generateQueryExtensionForOglas());
		List<Brend> brendovi = brendService.getAllBrendoviAutomobila();
		Brend svi = new Brend();
		svi.setIdBrenda(0);
		svi.setNaziv("Svi");
		brendovi.add(svi);
		model.addAttribute("brendovi", brendovi);
		model.addAttribute("oglasi", oglasi);

		return "korisnik/sviOglasi";
	}

	@RequestMapping(value = "/svi", method = RequestMethod.GET)
	public String sviOglasi(Model model) {
		List<Ogla> oglasi = oglasService.getAllOglasi();
		List<Brend> brendovi = brendService.getAllBrendoviAutomobila();
		Brend svi = new Brend();
		svi.setIdBrenda(0);
		svi.setNaziv("Svi");
		brendovi.add(svi);
		model.addAttribute("brendovi", brendovi);
		model.addAttribute("oglasi", oglasi);

		return "korisnik/sviOglasi";
	}

	@RequestMapping(value = "/oglasSlika/{id_oglas}")
	@ResponseBody
	public byte[] getImageKorisnika(@PathVariable int id_oglas) {

		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		Automobil automobil = oglas.getAutomobil();

		return automobil.getSlikaAutomobila();
	}

	@GetMapping(value = "/detaljiOglasa")
	public String getOglasByID(@RequestParam(value = "id_oglas") int id_oglas, Model m) {
		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		Ponuda ponuda = ponudaService.getTrenutnuPonuduZaOglasId(id_oglas);

		List<Komentar> komentariZaOglas = komentarService.getKomentariZaOglas(oglas);
		m.addAttribute("komentari", komentariZaOglas);

		m.addAttribute("oglas", oglas);

		if (ponuda != null) {
			m.addAttribute("ponuda", ponuda);
		} else {
			m.addAttribute("ponuda", new Ponuda());
			m.addAttribute("nemaPonuda", "Za oglas trenutno ne postoje ponude.");
		}

		return "korisnik/detaljiOglasa";
	}

	@PostMapping("/dodajPonudu/{id_oglas}")
	public RedirectView dodajPonuduZaOglas(Ponuda ponuda, @PathVariable("id_oglas") int id_oglas, Model m,
			RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		Double ponudaPare = Double.parseDouble(request.getParameter("ponudaPare"));
		ponuda.setPonudaPare(ponudaPare);
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		ponuda.setOgla(oglas);
		ponuda.setKorisnik(korisnik);
		boolean dodata = ponudaService.dodajNovuPonudu(ponuda);

		redirectAttributes.addFlashAttribute("ponudaDodata", dodata);

		redirectAttributes.addAttribute("id_oglas", ponuda.getOgla().getIdOgla());

		return new RedirectView("/oglas/detaljiOglasa", true);
	}

	@GetMapping(value = "/prihvatiPonudu/{id_oglas}")
	public RedirectView prihvatiPonudu(@PathVariable("id_oglas") int id_oglas, Model model,
			RedirectAttributes redirectAttributes) {
		oglasService.prihvatiPonuduZaOglas(id_oglas);

		model.addAttribute("uspesnoPrihvacenaPonuda", "Prihvatili ste ponudu za vas oglas");

		redirectAttributes.addAttribute("id_oglas", id_oglas);

		return new RedirectView("/oglas/detaljiOglasa", true);
	}

	@RequestMapping(value = "/izbrisiOglas/{id_oglas}")
	public String izbrisiOglas(@PathVariable("id_oglas") int id_oglas, Model model) {
		List<Komentar> komentari = komentarService.getKomentariZaOglasId(id_oglas);
		List<Ponuda> ponude = ponudaService.getAllPonudeByIdOgla(id_oglas);
		Automobil automobil = automobilService.getAutomobilByIdOglasa(id_oglas);
		List<Automobil> automobili = aCR.sviAutomobiliOglasa(id_oglas);
		Ogla oglas = oglasService.getOglasWithId(id_oglas);

		if (ponude.size() != 0) {
			for (Ponuda p : ponude) {
				pCR.delete(p.getIdPonude());
			}
		}
		if (komentari.size() != 0) {
			for (Komentar k : komentari) {
				kCR.delete(k.getIdKomentara());
			}
		}
		oCR.delete(oglas.getIdOgla());
		if (automobili.size() != 0) {
			for (Automobil a : automobili) {
				aCR.delete(a.getIdAutomobila());
			}
		}

		// aCR.delete(automobil.getIdAutomobila());

		return "redirect: /LibWeb/oglas/svi";
	}

	@PostMapping(value = "/dodajKomentar/{id_oglas}")
	public RedirectView addKomentar(@ModelAttribute("komentar") Komentar komentar, @PathVariable int id_oglas,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		Automobil nakit = automobilService.getAutomobilByIdOglasa(id_oglas);

		oglas.setAutomobil(nakit);
		komentar.setKorisnik(korisnik);
		komentar.setOgla(oglas);

		LocalDateTime ldt = LocalDateTime.now();
		komentar.setDatumVreme(java.sql.Timestamp.valueOf(ldt));

		komentarService.dodajKomentar(komentar);
		redirectAttributes.addAttribute("id_oglas", id_oglas);

		return new RedirectView("/oglas/detaljiOglasa", true);
	}

	@ModelAttribute("oglas")
	public Ogla getOglas() {
		return new Ogla();
	}

	@ModelAttribute("komentar")
	public Komentar getKomentar() {
		return new Komentar();
	}

	@ModelAttribute("oglasSearchDto")
	public OglasSearchDto getOglasSearchDto() {
		return new OglasSearchDto();
	}

	@ModelAttribute("ponuda")
	public Ponuda getPonuda() {
		return new Ponuda();
	}

	@Autowired
	public void setBrendService(IBrendService brendService) {
		this.brendService = brendService;
	}

	@Autowired
	public void setAutomobilService(IAutomobilService automobilService) {
		this.automobilService = automobilService;
	}

	@Autowired
	public void setOglasService(IOglasService oglasService) {
		this.oglasService = oglasService;
	}

	@Autowired
	public void setPonudaService(IPonudaService ponudaService) {
		this.ponudaService = ponudaService;
	}

	@Autowired
	public void setKomentarService(IKomentarService komentarService) {
		this.komentarService = komentarService;
	}

}