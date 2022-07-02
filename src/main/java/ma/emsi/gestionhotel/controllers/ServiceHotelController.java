package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.ServiceHotel;
import ma.emsi.gestionhotel.exception.ServiceHotelException;
import ma.emsi.gestionhotel.service.ServiceHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicehotel")
public class ServiceHotelController {


    @Autowired
    private ServiceHotelService serviceHotelService;


    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<ServiceHotel>> getAllServicesHotel() {
        return new ResponseEntity<>(serviceHotelService.getServicesHotel(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<ServiceHotel> getServiceHotelById(@PathVariable Integer id)throws ServiceHotelException {
        return new ResponseEntity<>(serviceHotelService.getServiceHotelById(id),HttpStatus.OK);
    }

    @GetMapping("/find/nom/{des}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<ServiceHotel> getServiceHotelByNom(@PathVariable String des) {
        return new ResponseEntity<>(serviceHotelService.getServiceHotelByNom(des),HttpStatus.OK);
    }
    @GetMapping("/find/prix/{prix}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<ServiceHotel>> getServicesHotelByPrix(@PathVariable Double prix) {
        return new ResponseEntity<>(serviceHotelService.getServicesHotelByPrix(prix),HttpStatus.OK);
    }

    @PostMapping("/createNewService")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<ServiceHotel> saveServiceHotel(@RequestBody ServiceHotel service) {
        return new ResponseEntity<>(serviceHotelService.saveServiceHotel(service), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<ServiceHotel> updateServiceHotel(@RequestBody ServiceHotel service) {
        return new ResponseEntity<>(serviceHotelService.updateServiceHotel(service), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteServiceHotelById(@PathVariable Integer id) {
        serviceHotelService.deleteServiceHotelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
