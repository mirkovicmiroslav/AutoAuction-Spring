package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Automobil;

@Repository
public interface IAutomobilCrudRepo extends CrudRepository<Automobil, Integer> {
	@Query("SELECT a FROM Automobil a JOIN FETCH a.oglas AS og WHERE og.idOgla =:idO")
	public Automobil findByIdOglasa(@Param("idO") int idOglasa);
	
	@Query("SELECT a FROM Automobil a JOIN FETCH a.oglas AS og WHERE og.idOgla =:idO")
	public List<Automobil> sviAutomobiliOglasa(@Param("idO") int idOglasa);
	
}
