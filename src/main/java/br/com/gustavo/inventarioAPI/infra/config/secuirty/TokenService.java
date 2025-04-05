package br.com.gustavo.inventarioAPI.infra.config.secuirty;

import br.com.gustavo.inventarioAPI.domain.entity.User;

public interface TokenService {
    String validateToken(String token);

    String generateToken(User user);

}
