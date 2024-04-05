package cl.leonelalejandroppp.api.users.services;

import cl.leonelalejandroppp.api.common.jwt.JWTService;
import cl.leonelalejandroppp.api.phones.dto.CreatePhoneDTO;
import cl.leonelalejandroppp.api.phones.entities.PhoneEntity;
import cl.leonelalejandroppp.api.phones.services.PhoneService;
import cl.leonelalejandroppp.api.users.dto.CreateUserDTO;
import cl.leonelalejandroppp.api.users.dto.UserListDTO;
import cl.leonelalejandroppp.api.users.entities.UserEntity;
import cl.leonelalejandroppp.api.users.enums.Role;
import cl.leonelalejandroppp.api.users.exceptions.EmailExistsException;
import cl.leonelalejandroppp.api.users.repositories.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserEntityRepository userRepository;
    private final PhoneService phoneService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public List<UserListDTO> getAllUsers () {
        List<UserEntity> list = this.userRepository.findAll();
        return this.getListUserDTO(list);
    }

    private List<UserListDTO> getListUserDTO (List<UserEntity> listEntities) {
        List<UserListDTO> list = new ArrayList<UserListDTO>();
        for (UserEntity userEntity : listEntities) {
            list.add(userEntity.toListDTOResponse());
        }
        return list;
    }

    public UserEntity createUser (CreateUserDTO createUserDTO) {
        this.alreadyExistsEmail(createUserDTO.getEmail());
        UserEntity userEntity = createUserDTO.toUserEntity();
        userEntity.setPassword(this.passwordEncoder.encode(createUserDTO.getPassword()));
        userEntity.setRole(Role.USER);
        String token = jwtService.createToken(userEntity);
        userEntity.setToken(token);
        this.userRepository.save(userEntity);
        this.savePhones(createUserDTO.getPhones(), userEntity);
        return userEntity;
    }



    private void savePhones (List<CreatePhoneDTO> createPhoneDTOS, UserEntity userEntity) {
        for(CreatePhoneDTO createPhoneDTO : createPhoneDTOS) {
            PhoneEntity phoneEntity = createPhoneDTO.toPhoneEntity();
            phoneEntity.setUserEntity(userEntity);
            this.phoneService.createPhone(phoneEntity);
        }
    }

    private void alreadyExistsEmail (String email) {
        Boolean exists = this.userRepository.findByEmail(email).isPresent();
        if(exists){
            throw new EmailExistsException();
        }
    }

    public UserEntity findByEmail (String email) {
        return this.userRepository.findByEmail(email).orElseThrow();
    }
}

