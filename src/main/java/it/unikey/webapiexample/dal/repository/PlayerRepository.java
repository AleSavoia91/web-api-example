package it.unikey.webapiexample.dal.repository;

import it.unikey.webapiexample.dal.entity.Player;
import it.unikey.webapiexample.dal.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Override
    List<Player> findAll();

    Optional<Player> findByPlayerFirstname(String name);

    Optional<Player> findByPlayerId(Long id);

    List<Player> findByPlayerCurrentTeamTeamId(Long teamId);

    List<Player> findAllByPlayerCurrentTeam(Team selectedTeam);

    @Override
    <S extends Player> S saveAndFlush(S entity);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);


}
