package br.imd.ufrn.market.service;

import br.imd.ufrn.market.Entity.Product;
import br.imd.ufrn.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Optional<Product> updateProduct(Product product) {
        if (product.getId() == null || !productRepository.existsById(product.getId())) {
            return Optional.empty();
        }

        Product existingProduct = productRepository.findById(product.getId()).orElse(product);

        if (product.getProductName() != null && !product.getProductName().equals(existingProduct.getProductName())) {
            existingProduct.setProductName(product.getProductName());
        }
        if (product.getBatch() != null && !product.getBatch().equals(existingProduct.getBatch())) {
            existingProduct.setBatch(product.getBatch());
        }
        if (product.getFabDate() != null && !product.getFabDate().equals(existingProduct.getFabDate())) {
            existingProduct.setFabDate(product.getFabDate());
        }
        if (product.getBrand() != null && !product.getBrand().equals(existingProduct.getBrand())) {
            existingProduct.setBrand(product.getBrand());
        }
        if (product.getExpDate() != null && !product.getExpDate().equals(existingProduct.getExpDate())) {
            existingProduct.setExpDate(product.getExpDate());
        }
        if (product.getSection() != null && !product.getSection().equals(existingProduct.getSection())) {
            existingProduct.setSection(product.getSection());
        }

        return Optional.of(productRepository.save(existingProduct));
    }

    @Transactional
    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
