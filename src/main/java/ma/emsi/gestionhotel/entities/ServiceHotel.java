package ma.emsi.gestionhotel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Entity
public class ServiceHotel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nomServicehotel;
    private Double prixService;
    @ManyToMany(mappedBy = "serviceshotel")
    private List<Reservation> reservations;
}
