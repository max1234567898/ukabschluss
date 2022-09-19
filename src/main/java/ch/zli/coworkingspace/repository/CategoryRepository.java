package ch.zli.coworkingspace.repository;

import ch.zli.coworkingspace.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findAll();
}
