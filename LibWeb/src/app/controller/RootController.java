package app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.repository.IKorisnikCrudRepo;
import app.repository.IUlogaRepo;
import app.service.UserSecurityService;
import model.Korisnik;
import model.Uloga;

@Controller
public class RootController {
	
	private UserSecurityService userSecurityService;
	private IUlogaRepo uR;
	@Autowired
	private IKorisnikCrudRepo kCR;

    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String getLogin() {    	
        return "login";
    }
    
    @GetMapping(value = "/register")
    public String getRegister() {
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveKorisnik(@ModelAttribute("korisnik") @Valid Korisnik korisnik, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
    	if(file != null) {
        	korisnik.setSlika(file.getBytes());
    	}

    	if(bindingResult.hasErrors()) {
    	    return "register";
    	}
    	
    	String lozinka = korisnik.getLozinka();
    	String lozinka2 = request.getParameter("lozinka2");
    	if (!lozinka.equals(lozinka2)) {
			model.addAttribute("nisuJednake", "Unete lozinke nisu jednake");
			return "register";
		}
    	
    	Uloga uloga = uR.findByIdUloge(1);
    	korisnik.setUloga(uloga);
    	
    	Korisnik noviKorisnik = userSecurityService.saveNewKorisnik(korisnik);

    	if(noviKorisnik == null){
    	    model.addAttribute("usernameExists",true);
    	    return "register";
        }
        return "login";
    }

    @GetMapping(value = "/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/dashboardAdmin")
    public String getDashboardAdmin(Model m,HttpSession session){
    	Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
    	Korisnik k = kCR.findByKorisnickoIme(korisnik.getKorisnickoIme());
    	if (k.getUloga().getIdUloge()!=2) {
    		return "login";
    	}
    	m.addAttribute("korisnik", k);
        return "dashboardAdmin";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @ModelAttribute("korisnik")
    public Korisnik getKorisnik(){
        return new Korisnik();
    }
    
    @Autowired
	public void setUserSecurityService(UserSecurityService userSecurityService) {
		this.userSecurityService = userSecurityService;
	}
    
    @Autowired
	public void setuR(IUlogaRepo uR) {
		this.uR = uR;
	}

}