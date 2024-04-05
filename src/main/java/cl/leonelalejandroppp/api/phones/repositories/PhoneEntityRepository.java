package cl.leonelalejandroppp.api.phones.repositories;

import cl.leonelalejandroppp.api.phones.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhoneEntityRepository extends JpaRepository<PhoneEntity, UUID> {
}
