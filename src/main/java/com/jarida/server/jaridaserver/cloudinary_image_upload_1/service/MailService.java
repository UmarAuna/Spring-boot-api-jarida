package com.jarida.server.jaridaserver.cloudinary_image_upload_1.service;

import org.springframework.stereotype.Service;

@Service
public class MailService {
/*
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;


    public Boolean send(String toEmail, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject(subject, "UTF-8");

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(text, true);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println("MailService.send: " + e.getMessage());
            return false;
        }
    }*/
}
