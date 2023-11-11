package org.softuni.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;
    private final String mobileleEmail;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, @Value("${mail.mobilele}") String mobileleEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.mobileleEmail = mobileleEmail;
    }

    @Override
    public void sendRegistrationEmail(String email,String userNames,String activationCode) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setFrom(mobileleEmail);
            helper.setReplyTo(mobileleEmail);
            helper.setSubject("Welcome to Mobilele!");
            helper.setText(generateRegistrationEmailBody(userNames,activationCode), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateRegistrationEmailBody(String userNames,String activationCode) {

        Context context = new Context();

        context.setVariable("username", userNames);
        context.setVariable("activation_code", activationCode);

        return templateEngine.process("registration-email", context);
    }
}
