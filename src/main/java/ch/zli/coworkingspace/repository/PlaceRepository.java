package ch.zli.coworkingspace.repository;

import ch.zli.coworkingspace.model.PlaceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends CrudRepository<PlaceEntity, UUID> {
    List<PlaceEntity> findAll();
}
