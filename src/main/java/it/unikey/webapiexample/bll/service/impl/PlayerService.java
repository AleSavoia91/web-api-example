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

import java.util.List;
import java.util.NoSuchElementException;
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
    public PlayerDTO getById(Long playerId) throws PlayerGenericException {
        logger.info("START PlayerService.getById()");

        try {
            return playerMapper.toDto(playerRepository.findByPlayerId(playerId).get());
        } catch (NoSuchElementException ex) {
            logger.error(ex.getMessage());
            throw new PlayerGenericException(ex.getMessage());
        }
    }

    @Override
    public PlayerDTO update(Long playerId, PlayerDTO newPlayerDto) throws PlayerGenericException {
        logger.info("START PlayerService.update()");
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            if (newPlayerDto.getPlayerFirstname() != null) {
                player.setPlayerFirstname(newPlayerDto.getPlayerFirstname());
            }
            if (newPlayerDto.getPlayerLastname() != null) {
                player.setPlayerLastname(newPlayerDto.getPlayerLastname());
            }
            if (newPlayerDto.getPlayerBirthdate() != null) {
                player.setPlayerBirthdate(newPlayerDto.getPlayerBirthdate());
            }

            playerRepository.saveAndFlush(player);
            return playerMapper.toDto(player);
        } else {
            logger.error("No player found at id: " + playerId);
            throw new PlayerGenericException("Player not found");
        }
    }

    @Override
    public void deleteById(Long playerId) throws PlayerGenericException {
        logger.info("START PlayerService.deleteById()");
        try{
            playerRepository.deleteById(playerId);
        } catch (NoSuchElementException ex) {
            logger.error("No player found at id: " + playerId);
            throw new PlayerGenericException(ex.getMessage());
        }
    }

}
