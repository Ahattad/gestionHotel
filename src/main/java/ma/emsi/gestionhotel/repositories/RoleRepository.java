package ma.emsi.gestionhotel.repositories;

import ma.emsi.gestionhotel.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNomRole(String user);
}
