package app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;

@Repository
public interface IKorisnikCrudRepo extends CrudRepository<Korisnik, Integer> {
	public List<Korisnik> findAll();
	public Korisnik findByKorisnickoIme(String korisnickoIme);
	public Korisnik findByIdKorisnika(int idKorisnika);
}
