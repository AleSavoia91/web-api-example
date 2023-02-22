package it.unikey.webapiexample.pl.controller;

import it.unikey.webapiexample.bll.dto.PlayerDTO;
import it.unikey.webapiexample.bll.service.impl.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<PlayerDTO>> getAll() {
        logger.info("START PlayerController.getAll()");
        List<PlayerDTO> allPlayers = service.getAll();
        return ResponseEntity.ok().body(allPlayers);
    }

    @PostMapping("/insert")
    public ResponseEntity<PlayerDTO> insertPlayer(@RequestBody PlayerDTO player) {
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

}
