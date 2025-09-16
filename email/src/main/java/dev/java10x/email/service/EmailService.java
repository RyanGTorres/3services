package dev.java10x.email.service;

import dev.java10x.email.entity.EmailEntity;
import dev.java10x.email.enums.EmailStatus;
import dev.java10x.email.repository.EmailRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    @Value("${EMAIL-NAME}")
    private String emailFrom;

    public void sendMail(EmailEntity emailEntity){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getEmailSubject());
            message.setText(emailEntity.getBody());
            javaMailSender.send(message);
            emailEntity.setEmailFrom(emailFrom);
            emailEntity.setSendedAt(java.time.LocalDateTime.now());
            emailEntity.setEmailStatus(EmailStatus.ENVIADO);
            System.out.println("Email sent successfully to " + emailEntity.getEmailTo());
        }
        catch (Exception e){
            emailEntity.setEmailStatus(EmailStatus.FALHOU);
            System.out.println("Error sending email: " + e.getMessage());
        }
        emailRepository.save(emailEntity);
    }
}
