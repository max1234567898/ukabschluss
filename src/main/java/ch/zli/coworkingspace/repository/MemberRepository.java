package ch.zli.coworkingspace.repository;

import ch.zli.coworkingspace.model.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends CrudRepository<MemberEntity, UUID> {

    Optional<MemberEntity> findByUsername(String username);

}
