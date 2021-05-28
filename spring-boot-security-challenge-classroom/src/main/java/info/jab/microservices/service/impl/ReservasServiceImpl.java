package info.jab.microservices.service.impl;

import info.jab.microservices.controller.ReservaRequest;
import info.jab.microservices.model.Reserva;
import info.jab.microservices.repository.ReservasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Service
public class ReservasServiceImpl {

    @Autowired
    private ReservasRepository reservasRepository;

    public String makeReserva(ReservaRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        LocalDate ld = LocalDate.now();

                switch (request.getDia()){
            case "Lunes":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
                break;
            case "Martes":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
                break;

            case "Miercoles":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
                break;

            case "Jueves":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
                break;

            case "Viernes":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
                break;
            case "Sabado":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
                break;

            case "Domingo":
                ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
        }

        Date date2 = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

        reservasRepository.makeReserva(currentPrincipalName, date2, request.getHora(), request.getPersonas());

        return "OK";


    }

    public List<Reserva> getReservas(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return reservasRepository.getReservas(currentPrincipalName);
    }

    public String deleteReservaById(int id){

        reservasRepository.deleteById((long) id);

        return "OK";
    }

}
