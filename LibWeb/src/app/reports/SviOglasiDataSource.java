package app.reports;

import java.util.Date;
import java.util.List;

import app.repository.IOglasCrudRepo;
import app.repository.IPonudaCrudRepo;
import model.Ogla;
import model.Ponuda;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SviOglasiDataSource extends JRAbstractBeanDataSourceProvider{
	
	private List<Ogla> oglasi;
	private Date datumOd;
	private Date datumDo;
	private IOglasCrudRepo oCR;
	
	public SviOglasiDataSource(IOglasCrudRepo oCR, Date datumOd, Date datumDo) {
		super(Ponuda.class);
		this.oCR=oCR;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		oglasi = oCR.getOglasiUPeriodu(datumOd, datumDo);
		
		return new JRBeanCollectionDataSource(oglasi);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		oglasi = null;
	}

}