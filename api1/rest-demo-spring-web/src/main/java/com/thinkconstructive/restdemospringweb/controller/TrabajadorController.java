package com.thinkconstructive.restdemospringweb.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {
    TrabajadorRepository trabajadorRepository;

    public TrabajadorController(TrabajadorRepository trabajadorRepository){
        this.trabajadorRepository = trabajadorRepository;
    }

    @PutMapping
    public void createTrabajador(@RequestBody Trabajador trabajador){
        trabajadorRepository.save(trabajador);
        System.out.println("El trabajador %s-%s, %s ha sido añadido correctamente".formatted(trabajador.getId(), trabajador.getNombre(),trabajador.getApellido()));
    }

}
