package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.services.EmailBusiness;

import java.util.Random;

@RestController
public class EmailController {

    @Autowired
    EmailBusiness emailBusiness;

    @GetMapping ("/main/sendMail")
    public void sendActivateUserEmail() throws Exception {
        Random random = new Random();
        int randomWithNextInt = random.nextInt(999999);
        String otp = String.format("%06d", randomWithNextInt);
        String Email = "fireworkffb@gmail.com";
        String Name = "Samita";

        emailBusiness.sendActivateUserEmail(Email, Name, otp);

    }

}

