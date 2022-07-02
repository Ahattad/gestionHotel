package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.Chambre;
import ma.emsi.gestionhotel.entities.Reservation;
import ma.emsi.gestionhotel.entities.ServiceHotel;
import ma.emsi.gestionhotel.exception.ChambreException;
import ma.emsi.gestionhotel.exception.ReservationException;
import ma.emsi.gestionhotel.exception.ServiceChambreException;
import ma.emsi.gestionhotel.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ServiceHotelService serviceHotelService;

    @Autowired
    private ChambreService chambreService;

    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }
    public Reservation getReservationById(Integer id) throws ReservationException {
        return  reservationRepository.findById(id).orElseThrow(()->new ReservationException("reservation n'existe pas"));
    }
    public void deleteReservationById(Integer id) throws ReservationException {
        Reservation res=getReservationById(id);
        res.getChambre().setAlloue(false);
        reservationRepository.deleteById(id);
    }
    public Reservation saveReservation(Reservation reservation) throws ReservationException, ChambreException {
        Chambre ch=chambreService.getChambreById(reservation.getChambre().getId());
        if(!ch.isAlloue()){
            reservation.setChambre(ch);
            ch.setAlloue(true);
            chambreService.saveChambre(ch);
            return reservationRepository.save(reservation);
        }else{
            throw new ReservationException("chambre n'est pas dispo");
        }
    }

    public List<Reservation> findReservationsByDatedebutAndDatefin(LocalDate debutDate, LocalDate finalDate)throws ReservationException {
        return reservationRepository.findAllByDateDebutAndDateFin(debutDate,finalDate);
    }


    public List<Reservation> findReservationsByDatedebut(LocalDate date) throws ReservationException {
        return  reservationRepository.findAllByDateDebut(date);

    }

    public List<Reservation> findReservationsByDatefin(LocalDate date) throws ReservationException {
        return  reservationRepository.findAllByDateFin(date);

    }

    public Reservation updateReservation(Reservation reservation) throws ReservationException, ServiceChambreException, ChambreException {
        return saveReservation(reservation);
    }
    public void addServiceToReservation(Integer id1, Integer id2) throws ReservationException {
        Reservation res1=getReservationById(id1);
        ServiceHotel service= serviceHotelService.getServiceHotelById(id2);
        res1.addServiceToReservation(service);

    }
    public void addFactureToReservation(Integer id1, Integer id2) throws ReservationException {
        Reservation res1=getReservationById(id2);
        ServiceHotel service= serviceHotelService.getServiceHotelById(id2);
        res1.addServiceToReservation(service);
    }

    public void updateFacture(Integer id) throws ReservationException {
        Reservation reservation=getReservationById(id);
        reservation.getFacture().setPrixFacture(totalprix(reservation));

    }
    public Double totalprix(Reservation reservation) {
        Double totalprix=0.0;
        totalprix+= reservation.getChambre().getPrixChambre();
        totalprix+= reservation.getServiceshotel().stream().map(s->s.getPrixService()).mapToDouble(s->s).sum();
        return totalprix;
    }
}

