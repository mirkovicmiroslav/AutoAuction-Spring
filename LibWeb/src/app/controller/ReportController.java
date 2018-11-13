package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.reports.SviKorisniciDataSource;
import app.reports.SviOglasiDataSource;
import app.repository.IKorisnikCrudRepo;
import app.repository.IOglasCrudRepo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value="/reports")
public class ReportController {
	private JRDataSource jrDatasource;
	
	@Autowired 
	IOglasCrudRepo oCR;
	@Autowired
	IKorisnikCrudRepo kCR;

	public ReportController() throws JRException{
		
	}
	
	@RequestMapping(value="/sviOglasi.pdf", method=RequestMethod.GET)
	public String showReportPonude(Model m, Date datumOd, Date datumDo) throws JRException{
		
		SviOglasiDataSource ds = new SviOglasiDataSource(oCR, datumOd, datumDo);
		jrDatasource=ds.create(null);
		m.addAttribute("datasource", jrDatasource);
		m.addAttribute("format", "pdf");
		m.addAttribute("datumOd", datumOd);
		m.addAttribute("datumDo", datumDo);
		
		return "rpt_sviOglasiUPeriodu";
	}
	
	@RequestMapping(value="/SviKorisnici.pdf", method=RequestMethod.GET)
	public String showReportKorisnici(Model m) throws JRException{
		
		SviKorisniciDataSource ds = new SviKorisniciDataSource(kCR);
		jrDatasource=ds.create(null);
		m.addAttribute("datasource", jrDatasource);
		m.addAttribute("format", "pdf");
		m.addAttribute("aukcije", "Aukcije automobila");
		
		return "rpt_sviKorisnici";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
}