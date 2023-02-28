package it.unikey.webapiexample.pl.controller;

import it.unikey.webapiexample.bll.dto.PlayerDTO;
import it.unikey.webapiexample.bll.exception.PlayerGenericException;
import it.unikey.webapiexample.bll.service.impl.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService service;

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        logger.info("START PlayerController.getAll()");
        List<PlayerDTO> allPlayers = service.getAll();
        return ResponseEntity.ok().body(allPlayers);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long playerId) {
        logger.info("START PlayerController.getPlayerById()");
        try{
            PlayerDTO player = service.getById(playerId);
            return ResponseEntity.ok().body(player);
        } catch (PlayerGenericException ex){
            logger.error("Player not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO player) {
        logger.info("START PlayerController.insertPlayer()");
        PlayerDTO insertedPlayer = service.insert(player);
        return ResponseEntity.ok().body(insertedPlayer);
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<PlayerDTO>> getAllPlayersByTeam(@PathVariable Long teamId) {
        logger.info("START PlayerController.getAllPlayersByTeam()");
        List<PlayerDTO> players = service.getByPlayerCurrentTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    @PatchMapping("/update/{playerId}")
    public ResponseEntity<PlayerDTO> updatePlayer (@PathVariable Long playerId,@RequestBody PlayerDTO player) {
        logger.info("START PlayerController.updatePlayer()");
        try{
            PlayerDTO updatedPlayer = service.update(playerId, player);
            return ResponseEntity.ok().body(updatedPlayer);
        } catch (PlayerGenericException ex){
            logger.error("Player not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<Void> deletePlayer (@PathVariable Long playerId){
        logger.info("START PlayerController.deletePlayer()");
        try {
            service.deleteById(playerId);
            return ResponseEntity.noContent().build();
        } catch (PlayerGenericException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ExceptionHandler
    public ResponseEntity<Object> handlePlayerGenericException(PlayerGenericException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
