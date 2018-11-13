package app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Ogla;

@Repository
public interface IOglasCrudRepo extends CrudRepository<Ogla, Integer> {
	
	@Query("SELECT o FROM Ogla o WHERE o.korisnik.korisnickoIme =:ime")
	public List<Ogla> sviOglasiByKorisnickoIme(@Param("ime") String korisnickoIme);

	public Ogla findByIdOgla(int idOgla);
	
	@Query("SELECT o FROM Ogla o WHERE o.aktivan=1")
	public List<Ogla> findSviAktivniOglasi();
	
	@Query("SELECT o FROM Ogla o WHERE o.datumVreme BETWEEN :datOd AND :datDo AND o.aktivan = 1")
	public List<Ogla> getOglasiUPeriodu(@Param("datOd")Date datumOd, @Param("datDo")Date datumDo);

}