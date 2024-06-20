package com.gontijojess.AT_Infnet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private")
public class PrivateUsuarioController {

    @GetMapping("/teste")
        public String teste(){
        return "Testando autenticação e autorização";
        }

}
