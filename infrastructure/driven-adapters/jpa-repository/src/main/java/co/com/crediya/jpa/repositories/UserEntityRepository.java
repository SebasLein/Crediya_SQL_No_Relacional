package co.com.crediya.jpa.repositories;

import co.com.crediya.jpa.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByCorreoElectronico(String correoElectronico);
}
