package ma.emsi.gestionhotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Entity
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Boolean expiree;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE,optional = false)
    private Chambre chambre;
    @ManyToMany
    @JoinTable(name="serviceshotelreservation")
    @JsonIgnore
    private List<ServiceHotel> serviceshotel;
    @OneToOne(cascade = CascadeType.ALL,optional = false,orphanRemoval = true)
    @JoinColumn
    private Facture facture;

    public void addServiceToReservation(ServiceHotel service){
        this.getServiceshotel().add(service);
    }
    public void addServicesToReservation(List<ServiceHotel> service){
        this.getServiceshotel().addAll(service);
    }

    public void removeServiceToReservation(ServiceHotel service){
        this.getServiceshotel().remove(service);
    }
    public void removeServicesToReservation(List<ServiceHotel> service){
        this.getServiceshotel().removeAll(service);
    }
    public void addFactureToReservation(Facture facture){
        this.setFacture(facture);
    }







}
