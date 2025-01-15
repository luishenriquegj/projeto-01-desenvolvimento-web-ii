package br.imd.ufrn.market.DTO;

import br.imd.ufrn.market.constants.Gender;
import br.imd.ufrn.market.constants.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String password;
    private String name;
    private String email;
    private Gender gender;
    private UserRole role;
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy") // Adjust the pattern as needed
    private LocalDate dateOfBirth;


}