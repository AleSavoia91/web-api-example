package it.unikey.webapiexample.dal.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "players")
@RequiredArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String playerFirstname;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String playerLastname;

    @Column(nullable = false, unique = false)
    @Temporal(TemporalType.DATE)
    private Date playerBirthdate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "team_id")
    private Team playerCurrentTeam;

}
