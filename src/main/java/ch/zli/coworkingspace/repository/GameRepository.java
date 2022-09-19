package ch.zli.coworkingspace.repository;

import ch.zli.coworkingspace.model.GameEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends CrudRepository<GameEntity, UUID> {
    List<GameEntity> findAll();
}
