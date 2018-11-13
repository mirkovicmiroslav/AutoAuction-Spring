package app.reports;

import java.util.List;

import app.repository.IKorisnikCrudRepo;
import model.Korisnik;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SviKorisniciDataSource extends JRAbstractBeanDataSourceProvider {

	private List<Korisnik> korisnici;
	private IKorisnikCrudRepo kCR;

	public SviKorisniciDataSource(IKorisnikCrudRepo kCR) {
		super(Korisnik.class);
		this.kCR = kCR;
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		korisnici = kCR.findAll();

		return new JRBeanCollectionDataSource(korisnici);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		korisnici = null;
	}

}