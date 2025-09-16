package dev.java10x.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private UUID userId;
    private String emailSubject;
    private String emailTo;
    private String body;
};