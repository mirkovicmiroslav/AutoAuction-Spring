package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ponuda database table.
 * 
 */
@Entity
@Table(name="ponuda")
@NamedQuery(name="Ponuda.findAll", query="SELECT p FROM Ponuda p")
public class Ponuda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ponude")
	private int idPonude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_vreme")
	private Date datumVreme;

	@Column(name="ponuda_pare")
	private double ponudaPare;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_id_korisnika")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="Ogla_id_ogla")
	private Ogla ogla;

	public Ponuda() {
	}

	public int getIdPonude() {
		return this.idPonude;
	}

	public void setIdPonude(int idPonude) {
		this.idPonude = idPonude;
	}

	public Date getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}

	public double getPonudaPare() {
		return this.ponudaPare;
	}

	public void setPonudaPare(double ponudaPare) {
		this.ponudaPare = ponudaPare;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Ogla getOgla() {
		return this.ogla;
	}

	public void setOgla(Ogla ogla) {
		this.ogla = ogla;
	}

}