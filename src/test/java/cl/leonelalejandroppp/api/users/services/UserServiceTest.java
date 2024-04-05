package cl.leonelalejandroppp.api.users.services;

import cl.leonelalejandroppp.api.users.dto.UserListDTO;
import cl.leonelalejandroppp.api.users.entities.UserEntity;
import cl.leonelalejandroppp.api.users.repositories.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserEntityRepository userEntityRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(UserEntity.builder()
                .id(UUID.randomUUID())
                .email("hola@hola.cl")
                .build()
        );
        userEntityList.add(UserEntity.builder()
                .id(UUID.randomUUID())
                .email("hola2@hola.cl")
                .build()
        );

        when(userEntityRepository.findAll()).thenReturn(userEntityList);

        List<UserListDTO> result = userService.getAllUsers();

        assertEquals(userEntityList.size(), result.size());
    }
}