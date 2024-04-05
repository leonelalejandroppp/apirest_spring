package cl.leonelalejandroppp.api.phones.services;

import cl.leonelalejandroppp.api.phones.entities.PhoneEntity;
import cl.leonelalejandroppp.api.phones.repositories.PhoneEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {

    private final PhoneEntityRepository phoneRepository;

    public void createPhone (PhoneEntity phoneEntity) {
        this.phoneRepository.save(phoneEntity);
    }
}

