package com.thinkconstructive.restdemospringweb;

import com.thinkconstructive.restdemospringweb.model.*;
import com.thinkconstructive.restdemospringweb.repository.*;
import com.thinkconstructive.restdemospringweb.service.impl.CloudVendorServiceImpl;
import jakarta.annotation.PostConstruct;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class Listener {

    @Autowired
    CloudVendorServiceImpl cloudVendorService;
    @Autowired
    PicajeRepository picajeRepository;


    public static void main(String[] args){
        SpringApplication.run(Listener.class, args);
    }
    @Scheduled(cron = "0 */2 * * * *")
    @PostConstruct
    public void init(){
        List<CloudVendor> listaOperarios = cloudVendorService.getAllCloudVendors();
        List<Picaje> listaPicajes = picajeRepository.findAll();

        listaPicajes.sort(Comparator.comparing(Picaje::getFechaInicio));

        List<CloudVendor> operarioNopican = new ArrayList<>();

        LocalTime cincoManana = LocalTime.of(5, 0);
        LocalTime cuatroTarde = LocalTime.of(16,0);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate diaActual = LocalDate.now();
        int count = 0;
        for(CloudVendor operario : listaOperarios){
            List<Picaje> listaPicajesOperario = new ArrayList<>();
            for(Picaje pica : listaPicajes){
                if (pica.getOperario().equals(operario.getId())){
                    listaPicajesOperario.add(pica);
                }
            }
            if(listaPicajesOperario.size()>0){
                List<Picaje> listaPicasActuales = new ArrayList<>();
                List<Picaje> listaPicajesDiaAnterior = new ArrayList<>();
                for (Picaje pica : listaPicajesOperario){
                    LocalDate fecha = LocalDate.parse(pica.getFechaInicio().split(" ")[0], dateformatter);
                    if (fecha.equals(diaActual)){
                        listaPicasActuales.add(pica);
                    }
                    if (fecha.equals(diaActual.minusDays(1))){
                        listaPicajesDiaAnterior.add(pica);
                    }
                }

                if(listaPicasActuales.size() > 2){
                    String FechaEntrada = listaPicasActuales.getFirst().getFechaInicio();
                    LocalTime hora = LocalTime.parse((FechaEntrada.split(" ")[1]), timeFormatter);
                    if(hora.isAfter(LocalTime.MIDNIGHT) && hora.isBefore(cincoManana)) {
                        System.out.println("%s-%s > TurnoREAL:%s - %s | TurnoSupuesto:%s".formatted(operario.getId(),operario.getNombre(),"NOCHE",FechaEntrada,operario.getTurno()));
                        if(operario.getTurno().equals("Mañana")||operario.getTurno().equals("Tarde")||operario.getTurno().equals("sinNoche-M")||operario.getTurno().equals("sinNoche-T")){
                            operario.setTurno("Noche");
                        } else if (operario.getTurno().equals("sinMañana-T")) {
                            operario.setTurno("sinMañana-N");
                        } else if (operario.getTurno().equals("sinTarde-M")) {
                            operario.setTurno("sinTarde-N");
                        }
                    } else if(hora.isBefore(cuatroTarde)) {
                        System.out.println("%s-%s > TurnoREAL:%s - %s | TurnoSupuesto:%s".formatted(operario.getId(),operario.getNombre(),"MAÑANA",FechaEntrada,operario.getTurno()));
                        if(operario.getTurno().equals("Tarde")||operario.getTurno().equals("Noche")||operario.getTurno().equals("sinMañana-T")||operario.getTurno().equals("sinMañana-N")){
                            operario.setTurno("Mañana");
                        } else if (operario.getTurno().equals("sinTarde-N")) {
                            operario.setTurno("sinTarde-M");
                        } else if (operario.getTurno().equals("sinNoche-T")) {
                            operario.setTurno("sinNoche-M");
                        }
                    } else {
                        System.out.println("%s-%s > TurnoREAL:%s - %s | TurnoSupuesto:%s".formatted(operario.getId(),operario.getNombre(),"TARDE",FechaEntrada,operario.getTurno()));
                        if(operario.getTurno().equals("Mañana")||operario.getTurno().equals("Noche")||operario.getTurno().equals("sinTarde-M")||operario.getTurno().equals("sinTarde-N")){
                            operario.setTurno("Tarde");
                        } else if (operario.getTurno().equals("sinMañana-N")) {
                            operario.setTurno("sinMañana-T");
                        } else if (operario.getTurno().equals("sinNoche-M")) {
                            operario.setTurno("sinNoche-T");
                        }
                    }
                    cloudVendorService.updateCloudVendor(operario);
                }

                String MaquinaActual = listaPicajesOperario.getLast().getMaquina();
                MaquinaActual = switch(MaquinaActual) {
                    case "SERA" -> "autos";
                    case "BORD" -> "bordado";
                    case "DIG" -> "digital";
                    case "HORNO" -> "horno";
                    case "LASER" -> "laser";
                    case "OTROS" -> "otros";
                    case "PLANCH" -> "planchas";
                    case "SUBLICIN" -> "sublimacion";
                    case "SER" -> "pulpos";
                    case "TAMP" -> "tampo";
                    case "TERMO" -> "termo";
                    default -> "No tiene";
                };




                if(!Objects.equals(MaquinaActual, operario.getMaquina())){
                    operario.setMaquina(MaquinaActual);
                    cloudVendorService.updateCloudVendor(operario);
                    System.out.println("Operario: %s-%s maquina actualizada".formatted(operario.getId(),operario.getNombre()));
                    count ++;
                }
            }else{
                operarioNopican.add(operario);
            }

        }
        System.out.println(count+" Operarios han sido actualizados");



    }

}
