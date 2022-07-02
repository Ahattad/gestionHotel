package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.ServiceChambre;
import ma.emsi.gestionhotel.exception.ServiceChambreException;
import ma.emsi.gestionhotel.repositories.ServiceChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceChambreService {
    @Autowired
    private ServiceChambreRepository serviceChambreRepository;
    public List<ServiceChambre> getServicesChambre(){
        return serviceChambreRepository.findAll();
    }
    public ServiceChambre getServiceChambreById(Integer id) throws ServiceChambreException {
        return  serviceChambreRepository.findById(id).orElseThrow(()->new ServiceChambreException("ServiceChambre n'existe pas "));
    }

    public ServiceChambre getServiceChambreByNom(String nom) throws ServiceChambreException {
        return  serviceChambreRepository.findByNomServicechambre(nom).orElseThrow(()->new ServiceChambreException("ServiceChambre n'existe pas "));
    }
    public void deleteServiceChambreById(Integer id){
        serviceChambreRepository.deleteById(id);
    }
    public ServiceChambre saveServiceChambre(ServiceChambre serviceChambre){
        return serviceChambreRepository.save(serviceChambre);
    }
    public ServiceChambre updateService(ServiceChambre serviceChambre){
        return serviceChambreRepository.save(serviceChambre);
    }
}
