package ch.zli.coworkingspace.repository;

import ch.zli.coworkingspace.model.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends CrudRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByEmail(String email);

    List<MemberEntity> findAll();


}
