package br.imd.ufrn.market.controller;

import br.imd.ufrn.market.Entity.Client;
import br.imd.ufrn.market.Entity.Product;
import br.imd.ufrn.market.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.println;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Handles GET requests to "/clients".
     * Retrieves and returns a list of all clients from the database.
     *
     * @return a list of Client objects.
     */
    @GetMapping
    public List<Client> listClient(){
        return clientRepository.findAll();
    }

    /**
     * Handles GET requests to "/clients/{id}".
     * Retrieves and returns a client based on the provided product ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return a ResponseEntity containing the client object if found,
     *         or 404 Not Found if the client does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles POST requests to "/clients".
     * Creates a new client in the database using the provided client data.
     *
     * @param client the client data to be saved.
     * @return the saved Client object.
     */
    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    /**
     * Handles PUT requests to update an existing client.
     *
     * @param client the client data to be updated. The client must have a non-null ID.
     * @return a ResponseEntity containing the updated Client object if successful,
     *         or a 404 Not Found status if the client does not exist.
     * @throws IllegalArgumentException if the client ID is null, indicating that the client cannot be updated without an ID.
     */
    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {

        // Check if the client ID is null
        if (null == client.getId()) {
            throw new IllegalArgumentException("Id cannot be empty when update methods");
        }

        // Check if the client exists in the repository
        if (!clientRepository.existsById(client.getId())) {
            // Return 404 Not Found if the client does not exist
            return ResponseEntity.notFound().build();
        }

        // Update the client in the repository
        Client updatedClient = clientRepository.save(client);

        // Return the updated client with a 200 OK status
        return ResponseEntity.ok(updatedClient);
    }




    /**
     * Handles DELETE requests to "/clients/delete/{id}".
     * Deletes a client from the database based on the provided client ID.
     *
     * @param id the ID of the client to be deleted.
     * @return a ResponseEntity indicating the result of the operation:
     *         - 204 No Content if the deletion is successful.
     *         - 404 Not Found if the client does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        println("exists");
        if (clientRepository.existsById(id.longValue())) {
            clientRepository.deleteById(id.longValue());
            return ResponseEntity.noContent().build(); // Returns 204 No Content if deletion is successful
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if the client does not exist
        }
    }
}
