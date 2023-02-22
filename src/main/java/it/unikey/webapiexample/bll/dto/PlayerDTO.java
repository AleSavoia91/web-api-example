package it.unikey.webapiexample.bll.dto;

import lombok.*;

import java.util.Date;

@Data
public class PlayerDTO {

    private String playerFirstname;
    private String playerLastname;
    private Date playerBirthdate;
    private TeamDTO playerCurrentTeam;
}
