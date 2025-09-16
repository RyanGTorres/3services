package dev.java10x.email.dto;

import java.util.UUID;

public record EmailDto (
        UUID userId,
        String emailSubject,
        String emailTo,
        String body
) {}

