package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the brend database table.
 * 
 */
@Entity
@Table(name="brend")
@NamedQuery(name="Brend.findAll", query="SELECT b FROM Brend b")
public class Brend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_brenda")
	private int idBrenda;

	private String naziv;

	//bi-directional many-to-one association to Automobil
	@OneToMany(mappedBy="brend")
	private List<Automobil> automobils;

	public Brend() {
	}

	public int getIdBrenda() {
		return this.idBrenda;
	}

	public void setIdBrenda(int idBrenda) {
		this.idBrenda = idBrenda;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Automobil> getAutomobils() {
		return this.automobils;
	}

	public void setAutomobils(List<Automobil> automobils) {
		this.automobils = automobils;
	}

	public Automobil addAutomobil(Automobil automobil) {
		getAutomobils().add(automobil);
		automobil.setBrend(this);

		return automobil;
	}

	public Automobil removeAutomobil(Automobil automobil) {
		getAutomobils().remove(automobil);
		automobil.setBrend(null);

		return automobil;
	}

}