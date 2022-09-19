package ch.zli.coworkingspace.service;

import ch.zli.coworkingspace.model.BookingEntity;
import ch.zli.coworkingspace.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class BookingService {

    private final BookingRepository repository;

    BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public List<BookingEntity> loadAll() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    public Optional<BookingEntity> loadOne(UUID gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public BookingEntity create(BookingEntity booking) {
        log.info("Executing create game with id " + booking.getId() + " ...");
        return repository.save(booking);
    }

    public BookingEntity update(BookingEntity booking) {
        log.info("Executing update game with id " + booking.getId() + " ...");
        return repository.save(booking);
    }

    public void delete(UUID bookingId) {
        log.info("Executing delete game with id " + bookingId + " ...");
        repository.deleteById(bookingId);
    }

}
