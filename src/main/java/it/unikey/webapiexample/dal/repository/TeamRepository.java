package it.unikey.webapiexample.dal.repository;

import it.unikey.webapiexample.dal.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Long id);

    Optional<Team> findByTeamName(String name);

    @Override
    <S extends Team> S saveAndFlush(S entity);

    @Override
    boolean existsById(Long id);

    Optional<Team> existsTeamByTeamName(String name);

    @Override
    void deleteById(Long id);
}
