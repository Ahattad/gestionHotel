package ma.emsi.gestionhotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Entity
public class Chambre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private int numChambre;
    private boolean alloue=true;
    private String telChambre;
    private Double prixChambre;
    private String typeChambre;
    @OneToMany(mappedBy = "chambre")
    @JsonIgnore
    private List<Reservation> reservation;
    @ManyToMany
    @JoinTable(name="serviceschambrereservation")
    @JsonIgnore
    private List<ServiceChambre> servicechambre;


    public void addServicechambre(ServiceChambre service) {
        this.getServicechambre().add(service);
    }
    public void removeServicechambre(ServiceChambre service) {
        this.getServicechambre().remove(service);
    }
    public void addServiceschambre(List<ServiceChambre> service) {
        this.getServicechambre().addAll(service);
    }
    public void removeServiceschambre(List<ServiceChambre> service) {
        this.getServicechambre().removeAll(service);
    }


}
