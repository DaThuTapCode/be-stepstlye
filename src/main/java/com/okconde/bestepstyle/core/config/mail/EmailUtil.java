package com.okconde.bestepstyle.core.config.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Trong Phu on 6/5/2024 14:12:37
 *
 * @author Trong Phu
 */
@Service
@RequiredArgsConstructor
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public void sendPaymentConfirmationEmail(String to, String subject, Map<String, Object> model) throws MessagingException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();
        context.setVariables(model);

        String htmlContent = templateEngine.process("TemplateBookingMail", context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom("your-email@gmail.com");

        mailSender.send(mimeMessage);
    }

    public void sendBookingEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setText(htmlBody, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("your-email@gmail.com");

        mailSender.send(mimeMessage);
    }

    public void sendEmailWithAttachment(String to, String subject, String htmlBody, File attachment) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setText(htmlBody, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("your-email@gmail.com");

        helper.addAttachment(attachment.getName(), attachment);

        mailSender.send(mimeMessage);
    }

    public void sendEmailWithInlineImage(String to, String subject, String htmlBody, String imagePath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setText(htmlBody, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("your-email@gmail.com");

        ClassPathResource resource = new ClassPathResource(imagePath);
        helper.addInline("image001", resource);

        mailSender.send(mimeMessage);
    }

}
