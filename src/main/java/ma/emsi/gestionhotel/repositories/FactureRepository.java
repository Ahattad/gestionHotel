package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Integer> {
    List<Facture> findFacturesByPrixFacture(Double prix);
}
