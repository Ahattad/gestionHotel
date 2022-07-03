package ma.emsi.gestionhotel.service;

import ma.emsi.gestionhotel.entities.Role;
import ma.emsi.gestionhotel.entities.User;
import ma.emsi.gestionhotel.exception.UserException;
import ma.emsi.gestionhotel.repositories.RoleRepository;
import ma.emsi.gestionhotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolerepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Integer id) throws UserException {
        return userRepository.findById(id).orElseThrow(()->new UserException("Utilisateur n'existe pas"));
    }

    public List<User> getUsersByNom(String nom) throws UserException {
        List<User> clients = userRepository.findAllByNomUtilisateur(nom);
        if(clients.isEmpty())
            throw new UserException("Client "+nom+" n'existe pas");
        return clients;
    }

    public List<User> getUsersByPrenom(String prenom) throws UserException {
        List<User> clients = userRepository.findAllByPrenomUtilisateur(prenom);
        if(clients.isEmpty())
            throw new UserException("Client "+prenom+" n'existe pas");
        return clients;
    }


    public void deleteUserById(Integer id) throws UserException {
        User usr=userRepository.findById(id).orElseThrow(()->new UserException("Utilisateur avec "+id+"n'existe pas !"));
        userRepository.deleteById(id);
    }

    public User saveUser(User user) throws UserException {
        if (getUsers().contains(user)){
            throw new UserException("Utilisateur existe deja");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) throws UserException {
        User usrmod = getUserById(user.getId());
        return userRepository.save(user);
    }



    public User registerNewUser(User user) {
        Role role = rolerepository.findByNomRole("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepository.save(user);
    }

    public User registerNewReceptionniste(User user) {
        Role role = rolerepository.findByNomRole("Receptionniste");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepository.save(user);
    }

    public User registerNewAdmin(User user) {
        Role role = rolerepository.findByNomRole("Admin");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
