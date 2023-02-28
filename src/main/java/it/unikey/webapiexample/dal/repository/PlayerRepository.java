package it.unikey.webapiexample.dal.repository;

import it.unikey.webapiexample.dal.entity.Player;
import it.unikey.webapiexample.dal.exception.PlayerDAOException;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Override
    List<Player> findAll();

    Optional<Player> findByPlayerId(Long id);

    List<Player> findByPlayerCurrentTeamTeamId(Long teamId);

    @Override
    <S extends Player> S saveAndFlush(S entity);

    @Override
    void deleteById(Long playerId);
}
