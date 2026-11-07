package co.com.crediya.jpa.adapters;

import co.com.crediya.jpa.entities.UserEntity;
import co.com.crediya.jpa.repositories.UserEntityRepository;
import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserRepository {
    private final UserEntityRepository repository;

    @Override
    public boolean existsByCorreoElectronico(String correo) {
        return repository.existsByCorreoElectronico(correo);
    }

    @Override
    @Transactional // Garantiza la atomicidad de la operación de guardado
    public User save(User user) {
        // Mapeo Dominio -> Entidad
        UserEntity entity = UserEntity.builder()
                .nombres(user.getNombres())
                .apellidos(user.getApellidos())
                .fechaNacimiento(user.getFechaNacimiento())
                .direccion(user.getDireccion())
                .telefono(user.getTelefono())
                .correoElectronico(user.getCorreoElectronico())
                .salarioBase(user.getSalarioBase())
                .build();

        UserEntity savedEntity = repository.save(entity);

        // Mapeo Entidad -> Dominio
        user.setCorreoElectronico(savedEntity.getCorreoElectronico());
        // (Asumimos que el objeto User ya tiene sus datos listos para retornar)
        return user;
    }
}
