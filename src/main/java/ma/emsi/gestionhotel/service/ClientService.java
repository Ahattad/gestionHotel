package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.Client;
import ma.emsi.gestionhotel.entities.Reservation;
import ma.emsi.gestionhotel.exception.ClientException;
import ma.emsi.gestionhotel.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public List<Client> getClients(){
        return clientRepository.findAll();
    }
    public Client getClientById(Integer id) throws ClientException {
        return clientRepository.findById(id).orElseThrow(()-> new ClientException( "Client "+ id + "n'existe pas "));
    }
    public List<Client> getClientsByNom(String nom) throws ClientException {
        List<Client> clients = clientRepository.findAllByNomClient(nom);
        if(clients.isEmpty())
            throw new ClientException("Client "+nom+" n'existe pas");
        return clients;
    }

    public List<Client> getClientsByPrenom(String prenom) throws ClientException {
        List<Client> clients = clientRepository.findAllByPrenomClient(prenom);
        if(clients.isEmpty())
            throw new ClientException("Client "+prenom+" n'existe pas");
        return clients;
    }

    public void deleteClientById(Integer id) throws ClientException {
        Client clt=clientRepository.findById(id).orElseThrow(()->new ClientException("Client avec "+id+"n'existe pas !"));
        clientRepository.deleteById(id);
    }
    public Client saveClient(Client client) throws ClientException {
        if (getClients().contains(client)){
            throw new ClientException("Client existe deja");
        }
        return clientRepository.save(client);
    }
    public Client updateClient(Client client) throws ClientException {
        Client cltamod = getClientById(client.getId());
        return clientRepository.save(client);
    }
    public void addReservation(Reservation reservation,Client client) throws ClientException {
        Client client1 = getClientById(client.getId());
        client1.addReservationToClient(reservation);
    }
    public void removeReservation(Reservation reservation,Client client) throws ClientException {
        Client client1 = getClientById(client.getId());
        client1.removeReservationToClient(reservation);
    }

}
