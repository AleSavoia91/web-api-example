package it.unikey.webapiexample.bll.service.impl;

import it.unikey.webapiexample.bll.dto.PlayerDTO;
import it.unikey.webapiexample.bll.mapper.impl.PlayerDtoMapper;
import it.unikey.webapiexample.bll.mapper.impl.PlayerDtoMapperImpl;
import it.unikey.webapiexample.bll.mapper.impl.TeamDtoMapper;
import it.unikey.webapiexample.bll.mapper.impl.TeamDtoMapperImpl;
import it.unikey.webapiexample.dal.entity.Player;
import it.unikey.webapiexample.dal.entity.Team;
import it.unikey.webapiexample.dal.repository.PlayerRepository;
import it.unikey.webapiexample.dal.repository.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PlayerServiceTest {

    @Mock
    private PlayerRepository testPlayerRepository;
    @Mock
    private TeamRepository testTeamRepository;
    private PlayerService testPlayerService;
    private PlayerDtoMapper playerMapper;
    private TeamDtoMapper teamMapper;

    @BeforeEach
    void setUp() {
        playerMapper = new PlayerDtoMapperImpl();
        teamMapper = new TeamDtoMapperImpl();
        testPlayerService = new PlayerService(testPlayerRepository, testTeamRepository, playerMapper, teamMapper);
    }

    @Test
    void insertTest() {
        //given
        Player testPlayer = new Player();
        testPlayer.setPlayerCurrentTeam(new Team());
        testPlayer.setPlayerBirthdate(new Date());
        testPlayer.setPlayerFirstname("TEST");
        testPlayer.setPlayerLastname("TEST");
        PlayerDTO testPlayerDTO = playerMapper.toDto(testPlayer);

        //when
        testPlayerService.insert(testPlayerDTO);

        //then
        //argumentCapture
        ArgumentCaptor<Player> playerArgumentCaptor =
                ArgumentCaptor.forClass(Player.class);

        verify(testPlayerRepository).saveAndFlush(playerArgumentCaptor.capture());

        Player capturedPlayer = playerArgumentCaptor.getValue();

        assertThat(capturedPlayer)
                .usingRecursiveComparison()
                .isEqualTo(testPlayer);
    }

    @Test
    void getAllTest() {
        //when
        testPlayerService.getAll();
        //then
        verify(testPlayerRepository).findAll();
    }

    @Test
    @Disabled
    void getByPlayerCurrentTeamId() {
    }

    @Test
    @Disabled
    void getById() {
    }

    @Test
    @Disabled //disabilita test
    void update() {
    }

    @Test
    @Disabled
    void deleteById() {
    }
}