package senior.project.firework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import senior.project.firework.exceptions.EmailException;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class EmailBusiness {

    @Autowired
    private ResourceLoader resourceLoader;

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

        String subject = "Please, Identity verification.";
        emailService.send(email, subject, html);
    }

    public void sendApproveAlready(String email, String name) throws Exception {

        String html;
        try {
            html = readEmailTemplate("account-approve.html");
        } catch (IOException e) {
            throw EmailException.templateNotFound();
        }

        html = html.replace("${P_NAME}", name);

        String subject = "Your account was approved.";
        emailService.send(email, subject, html);
    }

    public void sendReject(String email, String name) throws Exception {

        String html;
        try {
            html = readEmailTemplate("account-reject.html");
        } catch (IOException e) {
            throw EmailException.templateNotFound();
        }

        html = html.replace("${P_NAME}", name);

        String subject = "Your account was disapproved.";
        emailService.send(email, subject, html);
    }

    public void sendAccountCantDelete(String email, String name) throws Exception {

        String html;
        try {
            html = readEmailTemplate("account-cannotdelete.html");
        } catch (IOException e) {
            throw EmailException.templateNotFound();
        }

        html = html.replace("${P_NAME}", name);

        String subject = "You can not delete account.";
        emailService.send(email, subject, html);
    }

    private String readEmailTemplate(String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:email/" + filename);

        InputStream inputStream = resource.getInputStream();

        Assert.notNull(inputStream, "Could not load template resource!");

        String email = null;

        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            email = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
        return email;
    }

}
