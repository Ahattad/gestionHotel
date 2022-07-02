package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.Chambre;
import ma.emsi.gestionhotel.entities.Client;
import ma.emsi.gestionhotel.entities.ServiceChambre;
import ma.emsi.gestionhotel.exception.ChambreException;
import ma.emsi.gestionhotel.exception.ClientException;
import ma.emsi.gestionhotel.exception.ServiceChambreException;
import ma.emsi.gestionhotel.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    public List<Chambre> getChambres(){
        return chambreRepository.findAll();
    }
    public Chambre getChambreById(Integer id) throws ChambreException {
        return  chambreRepository.findById(id).orElseThrow(()->new ChambreException("Chambre "+id+"n'existe pas "));
    }
    public List<Chambre> getChambresByDisponibilite(Boolean dispo) throws ChambreException {
        List<Chambre> chambres = chambreRepository.findAllByAlloue(dispo);
        if(chambres.isEmpty())
            throw new ChambreException(" aucun chambre n'est disponible");
        return chambres;
    }

    public List<Chambre> getChambresByPrix(double prix) throws ChambreException {
        List<Chambre> chambres = chambreRepository.findAllByPrixChambre(prix);
        if(chambres.isEmpty())
            throw new ChambreException("Chambre avec ce "+prix+" n'existe pas");
        return chambres;
    }

    public void deleteChambreById(Integer id)throws ChambreException {
        Chambre ch=getChambreById(id);
        chambreRepository.deleteById(id);
    }
    public Chambre saveChambre(Chambre chambre) throws ChambreException {
        /*if (getChambres().contains(chambre)){
            throw new ChambreException("Chambre existe deja");
        }*/
        return chambreRepository.save(chambre);
    }
    public Chambre updateChambre(Chambre chambre)throws ChambreException {
        Chambre chambremod = getChambreById(chambre.getId());
        return chambreRepository.save(chambre);
    }

}
