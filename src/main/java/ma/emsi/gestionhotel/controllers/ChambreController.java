package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.Chambre;
import ma.emsi.gestionhotel.exception.ChambreException;
import ma.emsi.gestionhotel.exception.ServiceChambreException;
import ma.emsi.gestionhotel.service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambre")
public class ChambreController{

    @Autowired
    private ChambreService chambreService;


    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Chambre>> getAllChambres() throws ChambreException {
        List<Chambre> chambres = chambreService.getChambres();
        return new ResponseEntity<>(chambres, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Integer id) throws ChambreException {
        return new ResponseEntity<>(chambreService.getChambreById(id), HttpStatus.OK);
    }



    @GetMapping("/find/prix/{prix}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Chambre>> getChambresByPrix(@PathVariable("prix") Double prix) throws ChambreException {
        return new ResponseEntity<>(chambreService.getChambresByPrix(prix), HttpStatus.OK);
    }

    @GetMapping("/find/dispo/{disponibilite}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Chambre>> getChambresByDisponibilite(@PathVariable Boolean disponibilite) throws ChambreException {
        return new ResponseEntity<>(chambreService.getChambresByDisponibilite(disponibilite), HttpStatus.OK);
    }


    @PostMapping("/createNewChambre")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Chambre> saveChambre(@RequestBody Chambre chambre) throws ChambreException {
        return new ResponseEntity<>(chambreService.saveChambre(chambre), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Chambre> updateChambre(@RequestBody Chambre chambre) throws ChambreException {
        return new ResponseEntity<>(chambreService.updateChambre(chambre), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteChambreById(@PathVariable Integer id) throws ChambreException {
        chambreService.deleteChambreById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
