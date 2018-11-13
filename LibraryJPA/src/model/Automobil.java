package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the automobil database table.
 * 
 */
@Entity
@Table(name="automobil")
@NamedQuery(name="Automobil.findAll", query="SELECT a FROM Automobil a")
public class Automobil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_automobila")
	private int idAutomobila;

	private String boja;

	private int godiste;

	private int kilometraza;

	private String menjac;

	private String model;

	private String pogon;

	private int sedista;

	@Lob
	@Column(name="slika_automobila")
	private byte[] slikaAutomobila;

	private int snaga;

	@Column(name="tip_goriva")
	private String tipGoriva;

	private int vrata;

	//bi-directional many-to-one association to Brend
	@ManyToOne
	@JoinColumn(name="Brend_id_brenda")
	private Brend brend;

	//bi-directional many-to-one association to Ogla
	@OneToMany(mappedBy="automobil")
	private List<Ogla> oglas;

	public Automobil() {
	}

	public int getIdAutomobila() {
		return this.idAutomobila;
	}

	public void setIdAutomobila(int idAutomobila) {
		this.idAutomobila = idAutomobila;
	}

	public String getBoja() {
		return this.boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public int getGodiste() {
		return this.godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}

	public int getKilometraza() {
		return this.kilometraza;
	}

	public void setKilometraza(int kilometraza) {
		this.kilometraza = kilometraza;
	}

	public String getMenjac() {
		return this.menjac;
	}

	public void setMenjac(String menjac) {
		this.menjac = menjac;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPogon() {
		return this.pogon;
	}

	public void setPogon(String pogon) {
		this.pogon = pogon;
	}

	public int getSedista() {
		return this.sedista;
	}

	public void setSedista(int sedista) {
		this.sedista = sedista;
	}

	public byte[] getSlikaAutomobila() {
		return this.slikaAutomobila;
	}

	public void setSlikaAutomobila(byte[] slikaAutomobila) {
		this.slikaAutomobila = slikaAutomobila;
	}

	public int getSnaga() {
		return this.snaga;
	}

	public void setSnaga(int snaga) {
		this.snaga = snaga;
	}

	public String getTipGoriva() {
		return this.tipGoriva;
	}

	public void setTipGoriva(String tipGoriva) {
		this.tipGoriva = tipGoriva;
	}

	public int getVrata() {
		return this.vrata;
	}

	public void setVrata(int vrata) {
		this.vrata = vrata;
	}

	public Brend getBrend() {
		return this.brend;
	}

	public void setBrend(Brend brend) {
		this.brend = brend;
	}

	public List<Ogla> getOglas() {
		return this.oglas;
	}

	public void setOglas(List<Ogla> oglas) {
		this.oglas = oglas;
	}

	public Ogla addOgla(Ogla ogla) {
		getOglas().add(ogla);
		ogla.setAutomobil(this);

		return ogla;
	}

	public Ogla removeOgla(Ogla ogla) {
		getOglas().remove(ogla);
		ogla.setAutomobil(null);

		return ogla;
	}

}