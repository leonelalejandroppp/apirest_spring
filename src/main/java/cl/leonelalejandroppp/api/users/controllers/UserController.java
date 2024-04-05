package cl.leonelalejandroppp.api.users.controllers;

import cl.leonelalejandroppp.api.users.dto.UserListDTO;
import cl.leonelalejandroppp.api.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserListDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


}

