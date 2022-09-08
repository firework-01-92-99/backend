package senior.project.firework.services;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import senior.project.firework.exceptions.EmailException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class EmailBusiness {

    private final EmailService emailService;

    public EmailBusiness(EmailService emailService){
        this.emailService = emailService;
    }

    public void sendActivateUserEmail(String email, String name, String otp) throws Exception {

        String html;
        try {
            html = readEmailTemplate("email-activate-user.html");
        } catch (IOException e) {
            throw EmailException.templateNotFound();
        }

        html = html.replace("${P_NAME}", name);
        html = html.replace("${OTP}", otp);

        String subject = "";
        emailService.send(email, subject, html);
    }

    private String readEmailTemplate(String filename) throws IOException {
        File file = ResourceUtils.getFile("classpath:email/"+filename);
        return FileCopyUtils.copyToString(new FileReader(file));
    }

}
