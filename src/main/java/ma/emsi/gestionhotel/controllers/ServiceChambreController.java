package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.ServiceChambre;
import ma.emsi.gestionhotel.exception.ServiceChambreException;
import ma.emsi.gestionhotel.service.ServiceChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Servicechambre")
public class ServiceChambreController {

    @Autowired
    private ServiceChambreService serviceChambreService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<ServiceChambre>> getAllServicesChambre() {
        return new ResponseEntity<>(serviceChambreService.getServicesChambre(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<ServiceChambre> getServiceChambreById(@PathVariable Integer id) throws ServiceChambreException {
        return new ResponseEntity<>(serviceChambreService.getServiceChambreById(id), HttpStatus.OK);
    }

    @GetMapping("/find/nom/{nom}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<ServiceChambre> getServiceChambreByNom(@PathVariable String nom) throws ServiceChambreException {
        return new ResponseEntity<>(serviceChambreService.getServiceChambreByNom(nom), HttpStatus.OK);
    }

    @PostMapping("/createNewType")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<ServiceChambre> saveServiceChambre(@RequestBody ServiceChambre serviceChambre) {
        return new ResponseEntity<>(serviceChambreService.saveServiceChambre(serviceChambre), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<ServiceChambre> updateServiceChambre(@RequestBody ServiceChambre serviceChambre) {
        return new ResponseEntity<>(serviceChambreService.updateService(serviceChambre), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteServiceChambreById(@PathVariable Integer id) {
        serviceChambreService.deleteServiceChambreById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
