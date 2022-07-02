package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.Facture;
import ma.emsi.gestionhotel.exception.FactureException;
import ma.emsi.gestionhotel.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FactureService {
    @Autowired
    private FactureRepository factureRepository;
    public List<Facture> getFactures(){
        return factureRepository.findAll();
    }
    public Facture getFactureById(Integer id) throws FactureException {
        return  factureRepository.findById(id).orElseThrow(()->new FactureException("facture n'existe pas"));
    }

    public List<Facture> getFacturesByPrix(Double prix) throws FactureException {
        List<Facture> factures= factureRepository.findFacturesByPrixFacture(prix);
        if (factures.isEmpty()){
            throw new FactureException("aucun facture avec ce prix "+ prix);
        }else{
            return factures;
        }
    }

    public void deleteFactureById(Integer id) throws FactureException {
        Facture fac=getFactureById(id);
        factureRepository.deleteById(id);
    }
    public Facture saveFacture(Facture facture){
        return factureRepository.save(facture);
    }
    public Facture updateFacture(Facture facture) throws FactureException {
        Facture fac=getFactureById(facture.getId());
        return factureRepository.save(facture);
    }
}
