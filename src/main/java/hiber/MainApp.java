package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru")
              .setCar(new Car("Chevrolet Niva", 94563)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru")
              .setCar(new Car("Mazda CX-5 I", 634622)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru")
              .setCar(new Car("Peugeot 408", 239081)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru")
              .setCar(new Car("LADA Kalina II Cross", 346345)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user = userService.getUserByCarSerial("Peugeot 408", 239081);
      System.out.println(user);

      context.close();
   }
}
