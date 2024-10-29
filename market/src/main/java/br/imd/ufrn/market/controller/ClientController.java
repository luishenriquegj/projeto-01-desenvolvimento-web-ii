package br.imd.ufrn.market.controller;

import br.imd.ufrn.market.Entity.Client;
import br.imd.ufrn.market.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.sql.DriverManager.println;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping
    public List<Client> listClient(){
        return clientRepository.findAll();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @DeleteMapping("/clients/delete/{id}")
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
