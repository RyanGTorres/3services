package dev.java10x.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.java10x.user.entity.UserEntity;
import dev.java10x.user.exceptions.EmailAlreadyExistsException;
import dev.java10x.user.producer.UserProducer;
import dev.java10x.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserProducer userProducer;

    @Transactional
    public UserEntity saveUser(UserEntity userEntity) throws JsonProcessingException {
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + userEntity.getEmail());
        }
        userEntity = userRepository.save(userEntity);
        userProducer.enviarUsuarioProduce(userEntity);
        return userEntity;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public void findByEmail(String email) {
        userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
    public UserEntity updateUser(UUID id, UserEntity userEntity) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        BeanUtils.copyProperties(userEntity, existingUser, "userId");
        return userRepository.save(existingUser);
    }
}
