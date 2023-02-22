package it.unikey.webapiexample.bll.service.impl;

import it.unikey.webapiexample.bll.dto.PlayerDTO;
import it.unikey.webapiexample.bll.exception.PlayerGenericException;
import it.unikey.webapiexample.bll.mapper.impl.PlayerDtoMapper;
import it.unikey.webapiexample.bll.mapper.impl.TeamDtoMapper;
import it.unikey.webapiexample.bll.service.CrudService;
import it.unikey.webapiexample.dal.entity.Player;
import it.unikey.webapiexample.dal.entity.Team;
import it.unikey.webapiexample.dal.repository.PlayerRepository;
import it.unikey.webapiexample.dal.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService implements CrudService<PlayerDTO> {

    private final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;
    private final PlayerDtoMapper playerMapper;

    private final TeamDtoMapper teamMapper;

    //INSERIMENTO PLAYER A DB CON CONTROLLO SU ESISTENZA O MENO DEL TEAM (RELAZIONE PLAYER-TEAM)
    @Override
    public PlayerDTO insert(PlayerDTO dto) {
        logger.info("START PlayerService.insert()");
        String teamName = dto.getPlayerCurrentTeam().getTeamName();
        Player player = playerMapper.toEntity(dto);
        Optional<Team> teamOpt = teamRepository.findByTeamName(teamName);

        if (teamOpt.isPresent()) {
            Team playerTeam = teamOpt.get();
            player.setPlayerCurrentTeam(playerTeam);
        }

        return playerMapper.toDto(playerRepository.saveAndFlush(player));
    }

    @Override
    public List<PlayerDTO> getAll() {
        logger.info("START PlayerService.getAll()");
        return playerMapper.toDtos(playerRepository.findAll());
    }

    public List<PlayerDTO> getByPlayerCurrentTeamId(Long teamId) {
        logger.info("START PlayerService.getByPlayerCurrentTeamId()");
        return playerMapper.toDtos(playerRepository.findByPlayerCurrentTeamTeamId(teamId));
    }


    @Override
    public PlayerDTO getById(Long id) throws PlayerGenericException {
        return null;
    }

    @Override
    public PlayerDTO update(PlayerDTO dto) throws PlayerGenericException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws PlayerGenericException {

    }
}
