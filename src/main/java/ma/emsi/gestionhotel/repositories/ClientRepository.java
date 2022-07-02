package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
        List<Client> findAllByNomClient(String nom);
        List<Client> findAllByPrenomClient(String prenom);

}
