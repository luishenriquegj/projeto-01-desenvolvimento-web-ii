package br.imd.ufrn.market.Entity;


import br.imd.ufrn.market.constants.Section;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long  id;
  @Column(nullable = false)
  private String productName;
  @Column(nullable = false)
  private String brand;
  @Column(nullable = false)
  private LocalDate fabDate;
  @Column(nullable = true)
  private LocalDate expDate;
  @Column(nullable = false)
  private Section section;
  @Column(nullable = false)
  private String batch;

}
