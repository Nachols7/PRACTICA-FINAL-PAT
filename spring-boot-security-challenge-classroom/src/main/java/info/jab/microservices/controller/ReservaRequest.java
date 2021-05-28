package info.jab.microservices.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {

    @NonNull
    @NotEmpty
    private String dia;

    @NonNull
    @NotEmpty
    private String hora;

    private int personas;
}
