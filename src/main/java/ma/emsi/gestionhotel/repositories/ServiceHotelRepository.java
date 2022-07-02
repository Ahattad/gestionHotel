package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.ServiceHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceHotelRepository extends JpaRepository<ServiceHotel,Integer> {
    List<ServiceHotel> findAllByPrixService(Double price);
    Optional<ServiceHotel> findByNomServicehotel(String name);
}
