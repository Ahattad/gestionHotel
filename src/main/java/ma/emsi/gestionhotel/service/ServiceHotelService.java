package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.ServiceHotel;
import ma.emsi.gestionhotel.repositories.ServiceHotelRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceHotelService {
    @Autowired
    private ServiceHotelRepository serviceHotelRepository;
    public List<ServiceHotel> getServicesHotel(){
        return serviceHotelRepository.findAll();
    }
    public ServiceHotel getServiceHotelById(Integer id) throws ServiceException {
        return  serviceHotelRepository.findById(id).orElseThrow(()->new ServiceException("ServiceHotel n'existe pas  !"));
    }

    public ServiceHotel getServiceHotelByNom(String nom) throws ServiceException {
        return  serviceHotelRepository.findByNomServicehotel(nom).orElseThrow(()->new ServiceException("ServiceHotel n'existe pas  !"));
    }

    public List<ServiceHotel> getServicesHotelByPrix(Double prix) throws ServiceException {
        return  serviceHotelRepository.findAllByPrixService(prix);
    }

    public void deleteServiceHotelById(Integer id)throws ServiceException{
        serviceHotelRepository.deleteById(id);
    }
    public ServiceHotel saveServiceHotel(ServiceHotel service)throws ServiceException{
        return serviceHotelRepository.save(service);
    }
    public ServiceHotel updateServiceHotel(ServiceHotel serviceHotel)throws ServiceException{
        return serviceHotelRepository.save(serviceHotel);
    }
}
