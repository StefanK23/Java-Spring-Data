package SpringLAB.models;


import SpringLAB.models.models.User;
import SpringLAB.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User first = new User("Stefan", 23);
        userService.registerUser(first);

        User second = new User("Atanas", 24);
        userService.registerUser(second);
    }
}
