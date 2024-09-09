package com.thinkconstructive.restdemospringweb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;



import com.thinkconstructive.restdemospringweb.model.*;
import com.thinkconstructive.restdemospringweb.repository.CloudVendorRepository;
import com.thinkconstructive.restdemospringweb.service.impl.CloudVendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

import javax.swing.text.DateFormatter;

@SpringBootApplication
@EnableScheduling
public class Consultas {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CloudVendorServiceImpl cloudVendorService;
    @Autowired
    CloudVendorRepository cloudVendorRepository;
    public static void main(String[] args){
        SpringApplication.run(Consultas.class, args);
    }
     @PostConstruct
    @Scheduled(cron = "0 30 0 * * *")
    public void init(){
        List<CloudVendor> listaOperarios = cloudVendorRepository.findAll();
         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         for(CloudVendor operario : listaOperarios){
            String nombre = "";
            String apellido = "";
             try {
                nombre = operario.getNombre().split(",")[1];
                apellido = operario.getNombre().split(",")[0];
            }catch (Exception e){
                nombre = operario.getNombre();
                apellido = " ";
            }
             LocalDate fechaIni = LocalDate.parse(operario.getFechaInicio(), dateTimeFormatter);
             LocalDate fechaActual = LocalDate.now();

             long mesesExperiencia = ChronoUnit.MONTHS.between(fechaIni, fechaActual);
             Integer meses = (int) mesesExperiencia;

            Trabajador trabajador = new Trabajador(nombre,apellido,meses);
            updateOperario(trabajador);
        }
    }



        private void updateOperario(Trabajador trabajador) {
            String url = "http://localhost:8001/trabajador"; // URL de la otra API
            try {
                restTemplate.put(url, trabajador, String.class);
                System.out.println("Solicitud UPDATE enviada del operario -> " + trabajador.getNombre());
            } catch (Exception e) {
                System.out.println("EL LISTENER NO ESTÁ FUNCIONANDO !!!");
                e.printStackTrace();
            }
        }


}


