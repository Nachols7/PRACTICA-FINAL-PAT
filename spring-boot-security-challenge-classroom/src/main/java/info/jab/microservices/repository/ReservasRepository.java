package info.jab.microservices.repository;


import info.jab.microservices.controller.ReservaRequest;
import info.jab.microservices.model.Reserva;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public interface ReservasRepository extends CrudRepository<Reserva, Long> {


    @Modifying
    @Transactional
    @Query("INSERT INTO RESERVAS (USERNAME, START_DATE, START_HOUR, NUMBER_OF_PEOPLE) VALUES" +
            "(:username,:fecha,:hora,:personas);")
    void makeReserva(@Param("username")String username, @Param("fecha") Date date, @Param("hora")String hora, @Param("personas")int personas);

    @Query("SELECT * FROM RESERVAS WHERE USERNAME = :username")
    List<Reserva> getReservas(@Param("username") String username);
}
