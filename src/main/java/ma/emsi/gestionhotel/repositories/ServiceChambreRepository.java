package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.ServiceChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceChambreRepository extends JpaRepository<ServiceChambre,Integer> {
    Optional<ServiceChambre> findByNomServicechambre(String name);
}
