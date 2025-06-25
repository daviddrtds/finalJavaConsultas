package com.SGC.projeto_final_java.controller;

import org.springframework.stereotype.Controller;

@Controller
public class RoutesController {
    @org.springframework.web.bind.annotation.GetMapping("/")
    public String paginaPrincipal() {
        return "index";
    }

    @org.springframework.web.bind.annotation.GetMapping("/publico")
    public String paginaPublica() {
        return "publico";
    }

    @org.springframework.web.bind.annotation.GetMapping("/user")
    public String paginaUsuario() {
        return "user";
    }

    @org.springframework.web.bind.annotation.GetMapping("/admin")
    public String paginaAdmin() {
        return "admin";
    }

}
