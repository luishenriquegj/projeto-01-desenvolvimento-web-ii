package br.imd.ufrn.market.Entity;

import br.imd.ufrn.market.constants.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private List<Product> products;
    @Column(nullable = false)
    private Client client;
}
