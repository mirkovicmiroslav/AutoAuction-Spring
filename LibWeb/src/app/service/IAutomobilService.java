package app.service;

import model.Automobil;

public interface IAutomobilService {

	public Automobil dodajNoviAutomobil(Automobil automobil);

	public Automobil getAutomobilByIdOglasa(int idOglasa);
}