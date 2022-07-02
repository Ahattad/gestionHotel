package ma.emsi.gestionhotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
@Data @Entity @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class ServiceChambre implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nomServicechambre;
    @ManyToMany(mappedBy = "servicechambre")
    @JsonIgnore
    private List<Chambre> chambre;

}
