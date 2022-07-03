package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.Facture;
import ma.emsi.gestionhotel.exception.FactureException;
import ma.emsi.gestionhotel.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
public class FactureController {
    @Autowired
    private FactureService factureService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Facture>> getAllFactures() {
        return new ResponseEntity<>(factureService.getFactures(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Facture> getFactureById(@PathVariable Integer id) throws FactureException {
        return new ResponseEntity<>(factureService.getFactureById(id),HttpStatus.OK);
    }

    @GetMapping("/find/prix/{prix}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Facture>> getFactureByPrix(@PathVariable Double prix) throws FactureException {
        return new ResponseEntity<>(factureService.getFacturesByPrix(prix),HttpStatus.OK);
    }

    @PostMapping("/createNewFacture")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Facture> saveFacture(@RequestBody Facture facture) {
        return new ResponseEntity<>(factureService.saveFacture(facture), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Facture> updateFacture(@RequestBody Facture facture) throws FactureException {
        return new ResponseEntity<>(factureService.updateFacture(facture), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteFactureById(@PathVariable Integer id) throws FactureException {
        factureService.deleteFactureById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
