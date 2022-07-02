package ma.emsi.gestionhotel.controllers;
import ma.emsi.gestionhotel.entities.User;
import ma.emsi.gestionhotel.exception.UserException;
import ma.emsi.gestionhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) throws UserException {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping("/find/nom/{nom}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<User>> getUserByNom(@PathVariable String nom) throws UserException {
        return new ResponseEntity<>(userService.getUsersByNom(nom),HttpStatus.OK);
    }

    @GetMapping("/find/prenom/{prenom}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<User>> getUserByPrenom(@PathVariable String prenom) throws UserException {
        return new ResponseEntity<>(userService.getUsersByNom(prenom),HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException {
        if (getUserById(user.getId()).getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
        }
        else{
            throw new UserException("User n'existe pas");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) throws UserException {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
