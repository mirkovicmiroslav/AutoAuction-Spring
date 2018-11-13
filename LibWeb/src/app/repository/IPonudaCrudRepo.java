package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Ponuda;

@Repository
public interface IPonudaCrudRepo extends CrudRepository<Ponuda, Integer> {
	@Query("SELECT p FROM Ponuda p WHERE p.idPonude =(SELECT max(T.idPonude) FROM Ponuda T WHERE T.korisnik.korisnickoIme=p.korisnik.korisnickoIme AND T.ogla.idOgla=p.ogla.idOgla) AND p.korisnik.korisnickoIme=:kIme ORDER BY p.datumVreme DESC")
	public List<Ponuda> getAllPonudeByKorisnik(@Param("kIme") String korisnickoIme);
	
	@Query("SELECT p FROM Ponuda p WHERE p.ogla.idOgla=:oglasId ORDER BY p.ponudaPare")
	public List<Ponuda> getPonudeZaOglasId(@Param("oglasId")Integer IdOgla);
	
	@Query("SELECT p FROM Ponuda p WHERE p.korisnik.korisnickoIme =:kIme ORDER BY p.datumVreme DESC")
	public List<Ponuda> getAllByKorisnickoIme(@Param("kIme") String korisnickoIme);
	
	@Query("SELECT p FROM Ponuda p WHERE p.ogla.idOgla=:oglasId")
	public List<Ponuda> getAllPonudeByIdOgla(@Param("oglasId")Integer IdOgla);
	
	@Query("SELECT p FROM Ponuda p WHERE p.idPonude =(SELECT max(T.idPonude) FROM Ponuda T WHERE T.korisnik.korisnickoIme=p.korisnik.korisnickoIme AND T.ogla.idOgla=p.ogla.idOgla) AND p.korisnik.korisnickoIme=:kIme AND p.ogla.aktivan=0 ORDER BY p.datumVreme DESC")
	public List<Ponuda> getAllPonude(@Param("kIme") String korisnickoIme);
	
}