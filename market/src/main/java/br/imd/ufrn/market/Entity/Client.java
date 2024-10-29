package br.imd.ufrn.market.Entity;

import br.imd.ufrn.market.constants.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private Gender gender;

}
