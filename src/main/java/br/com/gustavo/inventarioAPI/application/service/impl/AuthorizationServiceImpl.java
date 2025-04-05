package br.com.gustavo.inventarioAPI.application.service.impl;

import br.com.gustavo.inventarioAPI.application.dto.auth.AuthenticationDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.ListUsersDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.LoginResponseDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.RegisterDTO;
import br.com.gustavo.inventarioAPI.application.service.AuthorizationService;
import br.com.gustavo.inventarioAPI.domain.entity.User;
import br.com.gustavo.inventarioAPI.domain.repository.UserRepository;
import br.com.gustavo.inventarioAPI.infra.config.secuirty.TokenService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationServiceImpl implements UserDetailsService, AuthorizationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthorizationServiceImpl(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    @Override
    public LoginResponseDTO authLogin(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token, ((User) auth.getPrincipal()).getRole());

    }

    @Override
    public void authRegister(RegisterDTO data) throws RuntimeException {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new RuntimeException("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(newUser);

    }

    @Override
    public List<ListUsersDTO> listAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new ListUsersDTO(user.getLogin(), user.getRole())).toList();
    }
}
