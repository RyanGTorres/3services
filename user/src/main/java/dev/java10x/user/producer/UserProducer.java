package dev.java10x.user.producer;
import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

   final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final String routingKey = "email-queue";

    public void publishEvent(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setEmailSubject("Welcome to Java10x");
        emailDto.setBody("Hello " + userModel.getName() + ",\n\nWelcome to Java10x! We are excited to have you on board.\n\nBest regards,\nJava10x Team");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
