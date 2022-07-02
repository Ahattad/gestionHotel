package ma.emsi.gestionhotel.controllers;

import ma.emsi.gestionhotel.entities.Role;
import ma.emsi.gestionhotel.exception.RoleException;
import ma.emsi.gestionhotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) throws RoleException {
        return new ResponseEntity<>(roleService.getRoleById(id),HttpStatus.OK);
    }


    @PostMapping({"/createNewRole"})
    @PreAuthorize("hasAnyRole('Admin')")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) throws RoleException {
        return new ResponseEntity<>(roleService.updateRole(role), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteRoleById(@PathVariable Integer id) throws RoleException {
        roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
