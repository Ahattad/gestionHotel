package ma.emsi.gestionhotel;

import ma.emsi.gestionhotel.entities.*;
import ma.emsi.gestionhotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class GestionHotelApplication implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    ChambreService chambreService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ServiceChambreService serviceChambreService;

    @Autowired
    ServiceHotelService serviceHotelService;
    public static void main(String[] args) {
        SpringApplication.run(GestionHotelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        Role role1=new Role(null,"Admin");
        Role role2=new Role(null,"User");

        roleService.createNewRole(role1);
        roleService.createNewRole(role2);

        User user1= new User(null,"ayman","hattad",4000.0,"ahattad@gmail.com","ayman123",new HashSet<>(),new ArrayList<>());
        userService.registerNewUser(user1);

        User admin1= new User(null,"sohayl","hattad",6000.0,"sohattad@gmail.com","sohayl123",new HashSet<>(),new ArrayList<>());
        userService.registerNewAdmin(admin1);
        User admin2= new User(null,"ayoub","labied",7000.0,"alabied","ayoub123",new HashSet<>(),new ArrayList<>());
        userService.registerNewAdmin(admin2);

        ServiceChambre serviceChambre1 = new ServiceChambre(null,"nettoyage",new ArrayList<>());
        serviceChambreService.saveServiceChambre(serviceChambre1);
        ServiceChambre serviceChambre2 = new ServiceChambre(null,"frigo",new ArrayList<>());
        serviceChambreService.saveServiceChambre(serviceChambre2);

        ServiceHotel serviceHotel1 = new ServiceHotel(null,"piscine",200.0,new ArrayList<>());
        serviceHotelService.saveServiceHotel(serviceHotel1);
        ServiceHotel serviceHotel2 = new ServiceHotel(null,"SPA",400.0,new ArrayList<>());
        serviceHotelService.saveServiceHotel(serviceHotel2);

        Chambre chambre1 = new Chambre(null,1,false,"0782338243",150.0,"Double",new ArrayList<>(),new ArrayList<>());
        chambre1.addServicechambre(serviceChambre1);
        chambreService.saveChambre(chambre1);
        Chambre chambre2 = new Chambre(null,2,false,"0734663243",170.0,"Simple",new ArrayList<>(),new ArrayList<>());
        chambre2.addServicechambre(serviceChambre1);
        chambreService.saveChambre(chambre2);
        Chambre chambre3 = new Chambre(null,3,false,"0426543243",190.0,"Double",new ArrayList<>(),new ArrayList<>());
        chambre3.addServicechambre(serviceChambre2);
        chambreService.saveChambre(chambre3);

        Client client1 = new Client(null,"said","hattad","marocain","0698338264",
                "dhattad@gmail.com","av osra 23112 temara Maroc",new ArrayList<>());
        clientService.saveClient(client1);
        Client client2 = new Client(null,"mohammed","hattad","marocain","0693258964",
                "mhattad@gmail.com","av batbota 3568 marrakech Maroc",new ArrayList<>());
        clientService.saveClient(client2);


        Reservation res1= new Reservation(null, LocalDate.now(),LocalDate.of(2022,07,05),false,client1,user1,chambre1,new ArrayList<>(),new Facture());
        res1.addServiceToReservation(serviceHotel1);
        res1.setFacture(new Facture());
        reservationService.saveReservation(res1);
        reservationService.updateFacture(res1.getId());

        Reservation res2= new Reservation(null, LocalDate.now(),  LocalDate.of(2022,07,07),true,client2,admin1,chambre2,new ArrayList<>(),new Facture());
        res2.addServiceToReservation(serviceHotel2);
        res2.setFacture(new Facture());
        reservationService.saveReservation(res2);
        reservationService.updateFacture(res2.getId());

    }
}
