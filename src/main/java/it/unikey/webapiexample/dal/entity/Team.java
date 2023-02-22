package it.unikey.webapiexample.dal.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "teams", uniqueConstraints = {
        @UniqueConstraint(columnNames = "teamName")
})
@RequiredArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @NotBlank
    @Column(nullable=false, unique=true)
    private String teamName;

    @OneToMany(mappedBy = "playerCurrentTeam",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private Set<Player> teamPlayers;

}
