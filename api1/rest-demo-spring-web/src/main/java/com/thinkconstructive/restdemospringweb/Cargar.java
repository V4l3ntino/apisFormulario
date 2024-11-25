package com.thinkconstructive.restdemospringweb;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.poiji.bind.Poiji;
import com.thinkconstructive.restdemospringweb.model.FormasAccidente;
import com.thinkconstructive.restdemospringweb.repository.FormasAccidenteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Cargar {
    @Autowired
    FormasAccidenteRepository formasAccidenteRepository;
    public static void main(String[] args) {
        SpringApplication.run(Cargar.class, args);
    }


    @PostConstruct
    public void init(){
        File archivo = new File("C:\\Users\\varmido\\Desktop\\APP_Formulario\\apisFormularioProject\\api1\\rest-demo-spring-web\\excel\\formasAccidente.xlsx");
        List<FormasAccidente> listaTiposAccidente = Poiji.fromExcel(archivo, FormasAccidente.class);

        for(FormasAccidente obj : listaTiposAccidente){
            formasAccidenteRepository.save(obj);
        }
        System.out.println("Objetos cargados en la Tabla");
    }

}
