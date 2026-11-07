package dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El campo nombres no puede ser nulo o vacío")
    private String nombres;

    @NotBlank(message = "El campo apellidos no puede ser nulo o vacío")
    private String apellidos;

    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;

    @NotBlank(message = "El correo electrónico no puede ser nulo o vacío")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String correoElectronico;

    @NotNull(message = "El salario base no puede ser nulo")
    @Min(value = 0, message = "El salario base debe ser mayor o igual a 0")
    @Max(value = 15000000, message = "El salario base no puede superar los 15.000.000")
    private BigDecimal salarioBase;

}