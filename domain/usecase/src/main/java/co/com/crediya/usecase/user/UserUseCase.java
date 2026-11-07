package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public User registrarUsuario(User usuario) {
        if (userRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
            throw new IllegalArgumentException("El correo electrónico ya se encuentra registrado por otro solicitante");
        }
        return userRepository.save(usuario);
    }
}