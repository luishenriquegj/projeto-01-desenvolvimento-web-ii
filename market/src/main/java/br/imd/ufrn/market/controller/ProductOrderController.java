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
    public ProductOrder postProductOrder(@RequestBody ProductOrder productOrder) {
        return productOrderService.postProductOrder(productOrder);
    }

    @PutMapping
    public ResponseEntity<ProductOrder> putProductOrder(@RequestBody ProductOrder productOrder) {
        Optional<ProductOrder> updatedProductOrder = productOrderService.putProductOrder(productOrder);
        return updatedProductOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductOrder(@PathVariable Long id) {
        if (productOrderService.deleteProductOrder(id)) {
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
    public ResponseEntity<ProductOrder> addProduct(@PathVariable Long orderId, @RequestBody Product product) {
        Optional<ProductOrder> updatedProductOrder = productOrderService.addProduct(orderId, product);
        return updatedProductOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{orderId}/removeProduct")
    public ResponseEntity<ProductOrder> removeProduct(@PathVariable Long orderId, @RequestBody Product product) {
        Optional<ProductOrder> updatedProductOrder = productOrderService.removeProduct(orderId, product);
        return updatedProductOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
