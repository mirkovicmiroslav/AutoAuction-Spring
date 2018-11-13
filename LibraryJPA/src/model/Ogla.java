package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the ogla database table.
 * 
 */
@Entity
@Table(name="ogla")
@NamedQuery(name="Ogla.findAll", query="SELECT o FROM Ogla o")
public class Ogla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ogla")
	private int idOgla;

	private int aktivan;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_vreme")
	private Date datumVreme;

	@Column(name="min_ponuda")
	@DecimalMin("0.00") 
	@DecimalMax("99999999999999999.00") 
	private double minPonuda;
	
	@NotEmpty(message="Ne sme biti prazno polje")
	private String naslov;

	private String tekst;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="ogla")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Automobil
	@ManyToOne
	@JoinColumn(name="Automobil_id_automobila")
	private Automobil automobil;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_id_korisnika")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Ponuda
	@OneToMany(mappedBy="ogla")
	private List<Ponuda> ponudas;

	public Ogla() {
	}

	public int getIdOgla() {
		return this.idOgla;
	}

	public void setIdOgla(int idOgla) {
		this.idOgla = idOgla;
	}

	public int getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}

	public Date getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}

	public double getMinPonuda() {
		return this.minPonuda;
	}

	public void setMinPonuda(double minPonuda) {
		this.minPonuda = minPonuda;
	}

	public String getNaslov() {
		return this.naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setOgla(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setOgla(null);

		return komentar;
	}

	public Automobil getAutomobil() {
		return this.automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Ponuda> getPonudas() {
		return this.ponudas;
	}

	public void setPonudas(List<Ponuda> ponudas) {
		this.ponudas = ponudas;
	}

	public Ponuda addPonuda(Ponuda ponuda) {
		getPonudas().add(ponuda);
		ponuda.setOgla(this);

		return ponuda;
	}

	public Ponuda removePonuda(Ponuda ponuda) {
		getPonudas().remove(ponuda);
		ponuda.setOgla(null);

		return ponuda;
	}

}