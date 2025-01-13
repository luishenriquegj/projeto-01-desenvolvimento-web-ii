package br.imd.ufrn.market.service;


import br.imd.ufrn.market.Entity.Product;
import br.imd.ufrn.market.Entity.ProductOrder;
import br.imd.ufrn.market.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public List<ProductOrder> getAllProductOrders() {
        return productOrderRepository.findAll();
    }

    public Optional<ProductOrder> getProductOrderById(Long id) {
        return productOrderRepository.findById(id);
    }

    @Transactional
    public ProductOrder postProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    @Transactional
    public Optional<ProductOrder> putProductOrder(ProductOrder productOrder) {
        if (productOrder.getId() == null || !productOrderRepository.existsById(productOrder.getId())) {
            return Optional.empty();
        }

        ProductOrder existingProductOrder = productOrderRepository.findById(productOrder.getId()).orElseThrow();

        if (productOrder.getCode() != null) {
            existingProductOrder.setCode(productOrder.getCode());
        }
        if (productOrder.getProducts() != null) {
            existingProductOrder.setProducts(productOrder.getProducts());
        }
        if (!productOrder.isActive()) {
            existingProductOrder.setActive(true);
        }
        if (productOrder.getClient() != null) {
            existingProductOrder.setClient(productOrder.getClient());
        }

        return Optional.of(productOrderRepository.save(existingProductOrder));
    }

    @Transactional
    public boolean deleteProductOrder(Long id) {
        if (productOrderRepository.existsById(id)) {
            productOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteLogic(Long id) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(id);
        if (productOrder.isPresent()) {
            ProductOrder order = productOrder.get();
            order.setActive(false);
            productOrderRepository.save(order);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<ProductOrder> addProduct(Long orderId, Product product) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(orderId);
        if (productOrder.isPresent()) {
            ProductOrder order = productOrder.get();
            order.getProducts().add(product);
            return Optional.of(productOrderRepository.save(order));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProductOrder> removeProduct(Long orderId, Product product) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(orderId);
        if (productOrder.isPresent()) {
            ProductOrder order = productOrder.get();
            order.getProducts().remove(product);
            return Optional.of(productOrderRepository.save(order));
        }
        return Optional.empty();
    }
}
