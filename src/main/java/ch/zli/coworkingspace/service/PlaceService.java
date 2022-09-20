package ch.zli.coworkingspace.service;

import ch.zli.coworkingspace.exception.GameNotFoundException;
import ch.zli.coworkingspace.model.PlaceEntity;
import ch.zli.coworkingspace.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PlaceService {

    private final PlaceRepository repository;

    PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<PlaceEntity> loadAll() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    @Transactional
    public Optional<PlaceEntity> loadOne(UUID placeId) {
        log.info("Executing find game with id " + placeId + " ...");
        return repository.findById(placeId);
    }

    @Transactional
    public PlaceEntity create(PlaceEntity place) {
        log.info("Executing create category with id " + place.getId() + " ...");
        return repository.save(place);
    }

    @Transactional
    public PlaceEntity update(PlaceEntity place) {
        log.info("Executing update game with id " + place.getId() + " ...");
        val placeId = place.getId();
        repository.findById(placeId).orElseThrow(() -> new GameNotFoundException("Place not found with id " + placeId));
        return repository.save(place);
    }

    @Transactional
    public void delete(UUID gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        repository.deleteById(gameId);
    }

}
