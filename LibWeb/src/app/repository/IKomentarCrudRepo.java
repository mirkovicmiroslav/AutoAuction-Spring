package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Komentar;

@Repository
public interface IKomentarCrudRepo extends CrudRepository<Komentar, Integer> {
	@Query("SELECT k FROM Komentar k WHERE k.ogla.idOgla=:idOglas")
	public List<Komentar> getKomentariZaOglasId(@Param("idOglas") Integer idOgla);
}
