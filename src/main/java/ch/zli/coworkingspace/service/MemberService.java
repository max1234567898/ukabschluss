package ch.zli.coworkingspace.service;

import ch.zli.coworkingspace.model.MemberEntity;
import ch.zli.coworkingspace.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository repository;

    MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<MemberEntity> loadAll() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    public Optional<MemberEntity> loadOne(UUID gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public MemberEntity create(MemberEntity member) {
        log.info("Executing create game with id " + member.getId() + " ...");
        return repository.save(member);
    }

    public MemberEntity update(MemberEntity member) {
        log.info("Executing update game with id " + member.getId() + " ...");
        return repository.save(member);
    }

    public void delete(UUID memberId) {
        log.info("Executing delete game with id " + memberId + " ...");
        repository.deleteById(memberId);
    }
}
