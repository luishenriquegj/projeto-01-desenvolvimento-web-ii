package br.imd.ufrn.market.controller;

import br.imd.ufrn.market.Entity.Order;
import br.imd.ufrn.market.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.println;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Handles GET requests to "/orders".
     * Retrieves and returns a list of all orders from the database.
     *
     * @return a list of Order objects.
     */
    @GetMapping
    public List<Order> listOrder(){
        return orderRepository.findAll();
    }

    /**
     * Handles GET requests to "/orders/{id}".
     * Retrieves and returns a order based on the provided product ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return a ResponseEntity containing the order object if found,
     * or 404 Not Found if the order does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles POST requests to "/orders".
     * Creates a new order in the database using the provided order data.
     *
     * @param order the order data to be saved.
     * @return the saved Order object.
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }








    /**
     * Handles DELETE requests to "/orders/delete/{id}".
     * Deletes a order from the database based on the provided order ID.
     *
     * @param id the ID of the order to be deleted.
     * @return a ResponseEntity indicating the result of the operation:
     *         - 204 No Content if the deletion is successful.
     *         - 404 Not Found if the order does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Integer id) {
        println("exists");
        if (orderRepository.existsById(id.longValue())) {
            orderRepository.deleteById(id.longValue());
            return ResponseEntity.noContent().build(); // Returns 204 No Content if deletion is successful
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if the order does not exist
        }
    }
}
