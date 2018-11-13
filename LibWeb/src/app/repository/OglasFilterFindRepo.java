package app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Ogla;

@Repository
@Transactional
public class OglasFilterFindRepo {
	private static final String GET_OGLASI_QUERY_START ="select o from Ogla o where o.aktivan = 1";
	
    @PersistenceContext
    EntityManager entityManager;
	
	public List<Ogla> getOglasiBy(String query){
    	Query q = entityManager.createQuery(GET_OGLASI_QUERY_START + query);
    	List<Ogla> oglasi = q.getResultList();
    	return oglasi;
    }
}