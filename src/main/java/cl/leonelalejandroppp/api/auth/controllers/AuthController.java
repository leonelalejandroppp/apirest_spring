package cl.leonelalejandroppp.api.auth.controllers;

import cl.leonelalejandroppp.api.auth.dto.LoginDTO;
import cl.leonelalejandroppp.api.auth.dto.LoginResponseDTO;
import cl.leonelalejandroppp.api.auth.service.AuthService;
import cl.leonelalejandroppp.api.users.dto.CreateUserDTO;
import cl.leonelalejandroppp.api.users.dto.UserCreatedDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(this.authService.login(loginDTO));
    }

    @PostMapping("register")
    public ResponseEntity<UserCreatedDTO> register(@RequestBody @Valid CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(this.authService.register(createUserDTO));
    }

}

