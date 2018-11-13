package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Uloga;

@Repository
public interface IUlogaRepo extends JpaRepository<Uloga, Integer> {
    public Uloga findByIdUloge(int idUloge);
}
