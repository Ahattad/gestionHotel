package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.Reservation;
import ma.emsi.gestionhotel.exception.ReservationException;
import ma.emsi.gestionhotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getReservations(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) throws ReservationException {
        return new ResponseEntity<>(reservationService.getReservationById(id),HttpStatus.OK);
    }

    @GetMapping("find/date/{dateDebut}/{dateFin}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> getReservationsByDatedebutAndDatefin(@PathVariable LocalDate dateDebut, @PathVariable LocalDate dateFin) throws ReservationException {
        return new ResponseEntity<>(reservationService.findReservationsByDatedebutAndDatefin(dateDebut,dateFin),HttpStatus.OK);
    }

    @GetMapping("find/datedebut/{date}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> getReservationsByDatedebut(@PathVariable LocalDate date) throws ReservationException {
        return new ResponseEntity<>(reservationService.findReservationsByDatedebut(date),HttpStatus.OK);
    }

    @GetMapping("find/datefin/{date}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> getReservationsByDatefin(@PathVariable LocalDate date) throws ReservationException {
        return new ResponseEntity<>(reservationService.findReservationsByDatefin(date),HttpStatus.OK);
    }


    @PostMapping("/createNewReservation")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) throws Exception {
        return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation) throws Exception {
        return new ResponseEntity<>(reservationService.updateReservation(reservation), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws Exception {
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
