package info.jab.microservices.controller;

import info.jab.microservices.model.Reserva;
import info.jab.microservices.service.ReservasService;
import info.jab.microservices.service.impl.ReservasServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j

@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ReservasController {

    @Autowired
    ReservasServiceImpl reservasService;

    @PostMapping("/reserva")
    public ResponseEntity<ReservaResponse> postReserva(
            @Valid @RequestBody ReservaRequest request,
            BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new ReservaResponse(reservasService.makeReserva(request)));
    }

    @GetMapping("/reserva")
    public ResponseEntity<ReservaResponse> getReservas(){

        final List<Reserva> reservas = reservasService.getReservas();

        ReservaResponse response = new ReservaResponse("");

        for(Reserva reserva : reservas){
            response.setMessage(response.getMessage() + " <li id="+reserva.getId().toString()+">" + reserva.toString() + "<button name="+reserva.getId().toString()+" onclick="+"reply_click(this.name)>X</button> </li>");
        }

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/reserva/{id_reserva}")
    public ResponseEntity<ReservaResponse> deleteReservaById(@PathVariable String id_reserva){
        final int id = Integer.parseInt(id_reserva);
        return ResponseEntity.ok().body(new ReservaResponse(reservasService.deleteReservaById(id)));
    }


}

