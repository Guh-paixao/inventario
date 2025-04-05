package br.com.gustavo.inventarioAPI.application.dto.auth;

import br.com.gustavo.inventarioAPI.domain.UserRole;

public record ListUsersDTO(String login, UserRole role) {
}
