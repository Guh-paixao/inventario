package br.com.gustavo.inventarioAPI.interfaces;

import br.com.gustavo.inventarioAPI.application.dto.auth.AuthenticationDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.ListUsersDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.LoginResponseDTO;
import br.com.gustavo.inventarioAPI.application.dto.auth.RegisterDTO;
import br.com.gustavo.inventarioAPI.application.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthorizationService authorizationService;

    public AuthenticationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<ListUsersDTO>> listUsers() {
        List<ListUsersDTO> users = authorizationService.listAllUsers();

        return ResponseEntity.ok(users);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        LoginResponseDTO user = authorizationService.authLogin(data);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        authorizationService.authRegister(data);

        return ResponseEntity.ok().build();
    }
}
