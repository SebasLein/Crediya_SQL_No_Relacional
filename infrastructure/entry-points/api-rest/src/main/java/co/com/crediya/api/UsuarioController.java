package co.com.crediya.api;

import dto.UsuarioRequestDTO;
import co.com.crediya.model.user.User;
import co.com.crediya.usecase.user.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // IMPORTANTE: Importar Slf4j
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Slf4j // Agregamos esta anotación para habilitar los logs de traza
public class UsuarioController {

    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        // Cumpliendo el criterio: Agregar logs de traza
        log.info("Iniciando registro para el usuario con correo: {}", request.getCorreoElectronico());
        
        User usuarioDominio = new User();
        usuarioDominio.setNombres(request.getNombres());
        usuarioDominio.setApellidos(request.getApellidos());
        usuarioDominio.setFechaNacimiento(request.getFechaNacimiento());
        usuarioDominio.setDireccion(request.getDireccion());
        usuarioDominio.setTelefono(request.getTelefono());
        usuarioDominio.setCorreoElectronico(request.getCorreoElectronico());
        usuarioDominio.setSalarioBase(request.getSalarioBase());

        User usuarioGuardado = userUseCase.registrarUsuario(usuarioDominio);
        
        log.info("Usuario registrado exitosamente en el sistema");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }
}