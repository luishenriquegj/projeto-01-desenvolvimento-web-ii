package br.imd.ufrn.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.ufrn.market.Entity.ProductOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository  extends JpaRepository<ProductOrder,Long>{
}
