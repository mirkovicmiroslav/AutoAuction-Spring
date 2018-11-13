package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Brend;

@Repository
public interface IBrendJpaRepo extends JpaRepository<Brend, Integer> {
	
	public List<Brend> findAll();
}
