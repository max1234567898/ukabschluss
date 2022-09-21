package ch.zli.coworkingspace.service;

import ch.zli.coworkingspace.exception.BookingNotFoundException;
import ch.zli.coworkingspace.model.BookingEntity;
import ch.zli.coworkingspace.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("Executing find all bookings ...");
        return repository.findAll();
    }

    public Optional<BookingEntity> loadOne(UUID gameId) {
        log.info("Executing find booking with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public BookingEntity create(BookingEntity booking) {
        log.info("Executing create booking with id " + booking.getId() + " ...");
        return repository.save(booking);
    }

    @Transactional
    public BookingEntity update(BookingEntity booking) {
        log.info("Executing update booking with id " + booking.getId() + " ...");
        val bookingId = booking.getId();
        repository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found with id " + bookingId));
        return repository.save(booking);
    }

    public void delete(UUID bookingId) {
        log.info("Executing delete booking with id " + bookingId + " ...");
        repository.deleteById(bookingId);
    }

}
