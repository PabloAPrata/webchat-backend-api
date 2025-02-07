package com.pabloprata.backend.webchat.infra.security;

public record DataAuthDTO(
        String login,
        String password
) {
}
