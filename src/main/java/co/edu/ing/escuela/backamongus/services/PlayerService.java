package co.edu.ing.escuela.backamongus.services;

import co.edu.ing.escuela.backamongus.classes.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    @Qualifier("playersMongoTemplate")
    private MongoTemplate mongoTemplate;

    // Crear un nuevo jugador
    public Player createPlayer(Player player) {
        return mongoTemplate.save(player);
    }

    // Leer un jugador por su ID
    public Player getPlayerById(String idPlayer) {
        return mongoTemplate.findById(idPlayer, Player.class);
    }

    // Leer todos los jugadores
    public List<Player> getAllPlayers() {
        return mongoTemplate.findAll(Player.class);
    }

    // Actualizar un jugador
    public Player updatePlayer(Player player) {
        Query query = new Query(Criteria.where("idPlayer").is(player.getIdPlayer()));
        Update update = new Update()
                .set("name", player.getName())
                .set("isImpostor", player.getIsImpostor())
                .set("tasks", player.getTasks())
                .set("idSession", player.getIdSession())
                .set("isAlive", player.getIsAlive());
        mongoTemplate.updateFirst(query, update, Player.class);
        return getPlayerById(player.getIdPlayer());
    }

    // Borrar un jugador por su ID
    public void deletePlayer(String idPlayer) {
        Query query = new Query(Criteria.where("idPlayer").is(idPlayer));
        mongoTemplate.remove(query, Player.class);
    }

    // Método adicional: Buscar jugadores por sesión
    public List<Player> getPlayersBySession(String idSession) {
        Query query = new Query(Criteria.where("idSession").is(idSession));
        return mongoTemplate.find(query, Player.class);
    }
    public boolean playerExists(String idPlayer) {
        Query query = new Query(Criteria.where("idPlayer").is(idPlayer));
        return mongoTemplate.exists(query, Player.class);
    }
}