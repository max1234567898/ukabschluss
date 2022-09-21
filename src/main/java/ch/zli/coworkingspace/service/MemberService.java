package ch.zli.coworkingspace.service;

import ch.zli.coworkingspace.exception.GameNotFoundException;
import ch.zli.coworkingspace.model.MemberEntity;
import ch.zli.coworkingspace.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("Executing find all members ...");
        return repository.findAll();
    }

    public Optional<MemberEntity> loadOne(UUID gameId) {
        log.info("Executing find member with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public MemberEntity create(MemberEntity member) {
        log.info("Executing create member with id " + member.getId() + " ...");
        return repository.save(member);
    }

    @Transactional
    public MemberEntity update(MemberEntity member) {
        log.info("Executing update member with id " + member.getId() + " ...");
        val memberId = member.getId();
        repository.findById(memberId).orElseThrow(() -> new GameNotFoundException("Member not found with id " + memberId));
        return repository.save(member);
    }

    public void delete(UUID memberId) {
        log.info("Executing delete member with id " + memberId + " ...");
        repository.deleteById(memberId);
    }
}
