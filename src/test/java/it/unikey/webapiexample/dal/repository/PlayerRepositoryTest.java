package it.unikey.webapiexample.dal.repository;

import it.unikey.webapiexample.bll.exception.PlayerNotFoundException;
import it.unikey.webapiexample.dal.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository testPlayerRepository;

    @AfterEach
    void tearDown() {
        testPlayerRepository.deleteAll();
    }

    @Test
    void findAll() {
    }

    @Test
    void findByPlayerIdTest() throws PlayerNotFoundException {
        //given
        Player testPlayer = new Player();
        testPlayer.setPlayerCurrentTeam(null);
        testPlayer.setPlayerId(1L);
        testPlayer.setPlayerBirthdate(new Date());
        testPlayer.setPlayerFirstname("TEST");
        testPlayer.setPlayerLastname("TEST");
        testPlayerRepository.saveAndFlush(testPlayer);

        //when
        Optional<Player> optPlayer = testPlayerRepository.findByPlayerId(testPlayer.getPlayerId());

        //then - comparazione tra due oggetti
        assertThat(optPlayer.get())
                .usingRecursiveComparison()
                .isEqualTo(testPlayer);
    }

    @Test
    void notfoundByPlayerIdTest() {
        //given
        Player testPlayer = new Player();
        testPlayer.setPlayerCurrentTeam(null);
        testPlayer.setPlayerId(1L);
        testPlayer.setPlayerBirthdate(new Date());
        testPlayer.setPlayerFirstname("TEST");
        testPlayer.setPlayerLastname("TEST");
        testPlayerRepository.saveAndFlush(testPlayer);

        //when
/*        assertThrows(PlayerNotFoundException.class,
                () -> {
                    testPlayerRepository.findByPlayerId(null);
                });*/

    }

    @Test
    void existByIdTest() {
        //given
        Player testPlayer = new Player();
        testPlayer.setPlayerCurrentTeam(null);
        testPlayer.setPlayerId(1L);
        testPlayer.setPlayerBirthdate(new Date());
        testPlayer.setPlayerFirstname("TEST");
        testPlayer.setPlayerLastname("TEST");
        testPlayerRepository.saveAndFlush(testPlayer);

        //when
        boolean isPresent = testPlayerRepository.existsById(testPlayer.getPlayerId());

        //then
        assertThat(isPresent).isTrue();
    }

    @Test
    void notExistByIdTest() {
        //given
        Long playerId = 20L;

        //when
        boolean isPresent = testPlayerRepository.existsById(playerId);

        //then
        assertThat(isPresent).isFalse();
    }

    @Test
    void saveAndFlush() {
    }

    @Test
    void deleteById() {
    }
}