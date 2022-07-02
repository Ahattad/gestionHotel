package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findAllByDateDebut(LocalDate date);
    List<Reservation> findAllByDateFin(LocalDate date);
    List<Reservation> findAllByDateDebutAndDateFin(LocalDate d1,LocalDate d2);
}
