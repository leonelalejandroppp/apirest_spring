package cl.leonelalejandroppp.api.auth.service;

import cl.leonelalejandroppp.api.auth.dto.LoginDTO;
import cl.leonelalejandroppp.api.auth.dto.LoginResponseDTO;
import cl.leonelalejandroppp.api.common.jwt.JWTService;
import cl.leonelalejandroppp.api.users.dto.CreateUserDTO;
import cl.leonelalejandroppp.api.users.dto.UserCreatedDTO;
import cl.leonelalejandroppp.api.users.entities.UserEntity;
import cl.leonelalejandroppp.api.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public UserCreatedDTO register (CreateUserDTO createUserDTO) {
        UserEntity userEntity = this.userService.createUser(createUserDTO);
        String token = jwtService.createToken(userEntity);
        UserCreatedDTO userCreatedDTO = userEntity.toCreatedResponse();
        userCreatedDTO.setToken(token);
        return userCreatedDTO;
    }

    public LoginResponseDTO login (LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        UserDetails user = this.userService.findByEmail(loginDTO.getEmail());
        String token = jwtService.createToken(user);
        return LoginResponseDTO.builder()
                .token(token)
                .build();
    }


}

