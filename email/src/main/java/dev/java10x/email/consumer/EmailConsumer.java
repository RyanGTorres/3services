package dev.java10x.email.consumer;

import dev.java10x.email.dto.EmailDto;
import dev.java10x.email.entity.EmailEntity;
import dev.java10x.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
@RequiredArgsConstructor
public class EmailConsumer {
    private final RabbitTemplate rabbitTemplate;
    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void listenEmailQueue(@Payload EmailDto emailDto){
        var emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDto, emailEntity);
        emailService.sendMail(emailEntity);
    }
}
