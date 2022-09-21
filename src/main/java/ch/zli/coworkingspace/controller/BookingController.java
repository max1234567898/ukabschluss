package ch.zli.coworkingspace.controller;

import ch.zli.coworkingspace.exception.BookingNotFoundException;
import ch.zli.coworkingspace.model.BookingEntity;
import ch.zli.coworkingspace.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Booking management endpoints")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(
            summary = "Get all bookings",
            description = "Loads all bookings from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping
    List<BookingEntity> loadAll() {
        return bookingService.loadAll();
    }

    @Operation(
            summary = "Get one specific booking",
            description = "Loads one specific booking from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    BookingEntity loadOne(@PathVariable UUID id) {
        return bookingService.loadOne(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with id " + id + " not found!"));
    }

    @Operation(
            summary = "Create a new booking",
            description = "Creates a new booking in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    BookingEntity create(@RequestBody BookingEntity entity) {
        return bookingService.create(entity);
    }

    @Operation(
            summary = "Update an existing booking",
            description = "Updates one specific and existing booking in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    BookingEntity update(@RequestBody BookingEntity updatedEntity, @PathVariable UUID id) {
        return bookingService.update(updatedEntity);
    }

    @Operation(
            summary = "Delete an existing booking",
            description = "Deletes one specific and existing booking in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        bookingService.delete(id);
    }
}
