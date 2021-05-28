package info.jab.microservices.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("RESERVAS")
@Data
public class Reserva {

    @Id
    @Column("ID")
    private Long id;

    @Column("USERNAME")
    private String username;

    @Column("START_DATE")
    private Date startDate;

    @Column("START_HOUR")
    private String hour;

    @Column("NUMBER_OF_PEOPLE")
    private Integer numberOfPeople;

    @Override
    public String toString(){
        System.out.println(startDate.toString());
        return username + " : " + startDate.toString() + "  " + hour + " para " + numberOfPeople.toString() + " personas";
    }

}