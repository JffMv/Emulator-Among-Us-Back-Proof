package co.edu.ing.escuela.backamongus.controllers;

import co.edu.ing.escuela.backamongus.services.PlayerService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/players")
public class RestControllerTasks {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/exists/{idPlayer}")
    public ResponseEntity<Boolean> playerExists(@PathVariable String idPlayer) {
        boolean exists = playerService.playerExists(idPlayer);
        return ResponseEntity.ok(exists);
    }
}