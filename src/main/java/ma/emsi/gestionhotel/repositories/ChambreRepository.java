package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Integer> {

    List<Chambre> findAllByAlloue(Boolean alloue);
    List<Chambre> findAllByPrixChambre(Double prix);

}
