package br.com.gustavo.inventarioAPI.application.dto.auth;

import br.com.gustavo.inventarioAPI.domain.UserRole;

public record LoginResponseDTO(String token, UserRole role) {
}
