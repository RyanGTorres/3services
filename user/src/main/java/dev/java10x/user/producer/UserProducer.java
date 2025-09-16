package dev.java10x.user.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.java10x.user.dto.EmailDto;
import dev.java10x.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserProducer {
    private final RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    private final String ROUT_NAME = "email-queue";

    public void enviarUsuarioProduce(UserEntity userEntity) throws JsonProcessingException {
        var emailSend  = new EmailDto();
        emailSend.setUserId(userEntity.getUserId());
        emailSend.setEmailTo(userEntity.getEmail());
        emailSend.setEmailSubject("Email de confirmação!");
        emailSend.setBody("Aqui seu link de confirmação: .... ");

        rabbitTemplate.setRoutingKey(ROUT_NAME);
        rabbitTemplate.convertAndSend(emailSend);
    }
}
