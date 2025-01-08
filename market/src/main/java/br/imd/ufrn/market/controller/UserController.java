package br.imd.ufrn.market.controller;


import br.imd.ufrn.market.dtos.UserDTO;
import br.imd.ufrn.market.service.UserService;
import br.imd.ufrn.market.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private final Logger log = Logger.getLogger(UserController.class.getName());

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> save(@RequestBody UserDTO userDTO) {
        try {
            Map<String, String> response = userService.saveUser(userDTO);
            if (!Objects.equals(response.get("code"), "202")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            return ResponseEntity.accepted().body(response);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body(ResponseUtils.makeMessage("Erro interno"));
        }
    }
}