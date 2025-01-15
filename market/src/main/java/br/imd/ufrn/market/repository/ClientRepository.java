package br.imd.ufrn.market.repository;

import br.imd.ufrn.market.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    UserDetails findByEmail(String login);

}
