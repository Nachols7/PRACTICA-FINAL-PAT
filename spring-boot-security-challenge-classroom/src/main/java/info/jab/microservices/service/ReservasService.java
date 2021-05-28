package info.jab.microservices.service;


import info.jab.microservices.controller.ReservaRequest;
import info.jab.microservices.model.Reserva;

import java.util.List;

public interface ReservasService {

    String makeReserva(ReservaRequest request);

    List<Reserva> getReservas();

    String DeleteReservaById(int id);

}
