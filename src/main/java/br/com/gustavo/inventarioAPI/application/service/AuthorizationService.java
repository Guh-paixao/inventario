package br.com.gustavo.inventarioAPI.application.service;

import br.com.gustavo.inventarioAPI.application.dto.auth.AuthenticationDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.ListUsersDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.LoginResponseDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.RegisterDTO;

import java.util.List;

public interface AuthorizationService {
    LoginResponseDTO authLogin(AuthenticationDTO data);

    void authRegister(RegisterDTO data);

    List<ListUsersDTO> listAllUsers();

}
