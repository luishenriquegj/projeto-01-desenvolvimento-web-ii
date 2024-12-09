package br.imd.ufrn.market.controller;

import br.imd.ufrn.market.Entity.Product;
import br.imd.ufrn.market.Entity.ProductOrder;
import br.imd.ufrn.market.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping
    public List<ProductOrder> getAllProductOrders() {
        return productOrderService.getAllProductOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOrder> getProductOrderById(@PathVariable Long id) {
        Optional<ProductOrder> productOrder = productOrderService.getProductOrderById(id);
        return productOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductOrder postPedido(@RequestBody ProductOrder productOrder) {
        return productOrderService.postPedido(productOrder);
    }

    @PutMapping
    public ResponseEntity<ProductOrder> putPedido(@RequestBody ProductOrder productOrder) {
        Optional<ProductOrder> updatedProductOrder = productOrderService.putPedido(productOrder);
        return updatedProductOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        if (productOrderService.deletePedido(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogic(@PathVariable Long id) {
        if (productOrderService.deleteLogic(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{orderId}/addProduct")
    public ResponseEntity<ProductOrder> adicionarProduto(@PathVariable Long orderId, @RequestBody Product product) {
        Optional<ProductOrder> updatedProductOrder = productOrderService.adicionarProduto(orderId, product);
        return updatedProductOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{orderId}/removeProduct")
    public ResponseEntity<ProductOrder> removerProduto(@PathVariable Long orderId, @RequestBody Product product) {
        Optional<ProductOrder> updatedProductOrder = productOrderService.removerProduto(orderId, product);
        return updatedProductOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
