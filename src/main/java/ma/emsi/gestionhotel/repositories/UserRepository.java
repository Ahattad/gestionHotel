package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String email);
    List<User> findAllByNomUtilisateur(String nom);
    List<User> findAllByPrenomUtilisateur(String prenom);
}
