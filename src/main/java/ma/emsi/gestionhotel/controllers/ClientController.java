package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.Client;
import ma.emsi.gestionhotel.exception.ClientException;
import ma.emsi.gestionhotel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) throws ClientException {
        return new ResponseEntity<>(clientService.getClientById(id),HttpStatus.OK);
    }
    @GetMapping("/find/nom/{nom}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> getClientsByNom(@PathVariable String nom) throws ClientException {
        return new ResponseEntity<>(clientService.getClientsByNom(nom),HttpStatus.OK);
    }

    @GetMapping("/find/prenom/{prenom}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> getClientsByPrenom(@PathVariable String prenom) throws ClientException {
        return new ResponseEntity<>(clientService.getClientsByPrenom(prenom),HttpStatus.OK);
    }


    @PostMapping("/createNewClient")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) throws ClientException {
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) throws ClientException {
        //if (getClientById(client.getId()).getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.CREATED);
        /*}
        else{
            throw new ClientException("Client n'existe pas");
        }*/
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteClientById(@PathVariable Integer id) throws ClientException {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
