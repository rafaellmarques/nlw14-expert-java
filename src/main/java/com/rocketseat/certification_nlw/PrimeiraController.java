package com.rocketseat.certification_nlw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraController")
public class PrimeiraController {

    @GetMapping("/retornarPrimeiraController")
    public Usuario retornoPrimeiraController() {
        // var usuario = new Usuario("Rafael", 38);
        // return usuario;
        return new Usuario("Rafael", 38);
    }

    @PostMapping("/meuPrimeiroPost")
    public String primeiroPost() {
        return "Meu primeiro Post";
    }

    record Usuario(String nome, int idade) {
    }
}
