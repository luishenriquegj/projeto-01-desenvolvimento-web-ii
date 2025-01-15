package br.imd.ufrn.market.controller;

import br.imd.ufrn.market.DTO.AuthenticationDTO;
import br.imd.ufrn.market.DTO.RegisterDTO;
import br.imd.ufrn.market.Entity.Client;
import br.imd.ufrn.market.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, ClientRepository clientRepository) {
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDTO data) {
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (this.clientRepository.findByEmail(data.getEmail()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Client newClient = new Client(data.getName(), data.getEmail(), encryptedPassword, data.getGender(), data.getRole(), data.getCpf(), data.getDateOfBirth());
        this.clientRepository.save(newClient);
        return ResponseEntity.ok().build();
    }
}
