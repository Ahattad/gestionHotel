package ma.emsi.gestionhotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nomClient;
    private String prenomClient;
    private String nationalite;
    private String telephone;
    private String email;
    private String adresse;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations;


    public void addReservationToClient(Reservation res){
        this.getReservations().add(res);
    }
    public void removeReservationToClient(Reservation res){
        this.getReservations().remove(res);
    }
    public void addReservationsToClient(List<Reservation> res){
        this.getReservations().addAll(res);
    }
    public void removeReservationsToClient(List<Reservation> res){
        this.getReservations().removeAll(res);
    }
}
