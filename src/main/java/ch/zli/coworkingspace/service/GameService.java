package ch.zli.coworkingspace.service;

import ch.zli.coworkingspace.repository.GameRepository;
import ch.zli.coworkingspace.model.GameEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class GameService {

    private final GameRepository repository;

    GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<GameEntity> loadAll() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    public Optional<GameEntity> loadOne(UUID gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public GameEntity create(GameEntity game) {
        log.info("Executing create game with id " + game.getId() + " ...");
        return repository.save(game);
    }

    public GameEntity update(GameEntity updatedGame) {
        log.info("Executing update game with id " + updatedGame.getId() + " ...");
        return repository.save(updatedGame);
    }

    public void delete(UUID gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        repository.deleteById(gameId);
    }

}
