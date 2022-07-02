package ma.emsi.gestionhotel.service;


import ma.emsi.gestionhotel.entities.Role;
import ma.emsi.gestionhotel.exception.RoleException;
import ma.emsi.gestionhotel.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository rolerepository;

    public Role createNewRole(Role role) {
        return rolerepository.save(role);
    }

    public List<Role> getRoles(){
        return rolerepository.findAll();
    }
    public Role getRoleById(Integer id) throws RoleException {
        return  rolerepository.findById(id).orElseThrow(()->new RoleException("Role n'existe pas"));
    }
    public void deleteRoleById(Integer id) throws RoleException {
        Role role=rolerepository.findById(id).orElseThrow(()->new RoleException("Role nexiste pas"));
        rolerepository.deleteById(id);
    }
    public Role updateRole(Role role) throws RoleException {
        Role rol=getRoleById(role.getId());
        return rolerepository.save(role);
    }
}
