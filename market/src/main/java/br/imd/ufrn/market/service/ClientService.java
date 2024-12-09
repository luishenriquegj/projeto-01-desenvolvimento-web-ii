package br.imd.ufrn.market.service;

import br.imd.ufrn.market.Entity.Client;
import br.imd.ufrn.market.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Optional<Client> updateClient(Client client) {
        if (client.getId() == null || !clientRepository.existsById(client.getId())) {
            return Optional.empty();
        }

        Client existingClient = clientRepository.findById(client.getId()).orElse(client);

        if (client.getName() != null && !client.getName().equals(existingClient.getName())) {
            existingClient.setName(client.getName());
        }
        if (client.getCpf() != null && !client.getCpf().equals(existingClient.getCpf())) {
            existingClient.setCpf(client.getCpf());
        }
        if (client.getDateOfBirth() != null && !client.getDateOfBirth().equals(existingClient.getDateOfBirth())) {
            existingClient.setDateOfBirth(client.getDateOfBirth());
        }
        if (client.getGender() != null && !client.getGender().equals(existingClient.getGender())) {
            existingClient.setGender(client.getGender());
        }

        return Optional.of(clientRepository.save(existingClient));
    }

    @Transactional
    public boolean deleteClientById(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
