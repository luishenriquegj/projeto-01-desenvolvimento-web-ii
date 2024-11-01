package br.imd.ufrn.market.controller;

import br.imd.ufrn.market.Entity.Client;
import br.imd.ufrn.market.Entity.Product;
import br.imd.ufrn.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Handles POST requests to "/products".
     * Creates a new product in the database using the provided product data.
     *
     * @param product the product data to be saved.
     * @return the saved Product object.
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    /**
     * Handles PUT requests to update an existing product.
     *
     * @param product the product data to be updated. The product must have a non-null ID.
     * @return the updated Product object.
     * @throws IllegalArgumentException if the product ID is null, indicating that the product cannot be updated without an ID.
     */
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Id cannot be empty when using update methods");
        }

        if (!productRepository.existsById(product.getId())) {
            return ResponseEntity.notFound().build();
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

        productRepository.save(existingProduct);
        return ResponseEntity.ok(existingProduct);
    }
    /**
     * Handles GET requests to "/products".
     * Retrieves and returns a list of all products from the database.
     *
     * @return a list of Product objects.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Handles GET requests to "/products/{id}".
     * Retrieves and returns a product based on the provided product ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return a ResponseEntity containing the Product object if found,
     *         or 404 Not Found if the product does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id.longValue());
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles DELETE requests to "/products/delete/{id}".
     * Deletes a product from the database based on the provided product ID.
     *
     * @param id the ID of the product to be deleted.
     * @return a ResponseEntity indicating the result of the operation:
     *         - 204 No Content if the deletion is successful.
     *         - 404 Not Found if the product does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Returns 204 No Content if deletion is successful
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if the product does not exist
        }
    }
}
