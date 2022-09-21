package ch.zli.coworkingspace.controller;

import ch.zli.coworkingspace.exception.BookingNotFoundException;
import ch.zli.coworkingspace.model.PlaceEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import ch.zli.coworkingspace.service.PlaceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/places")
@Tag(name = "Places", description = "Place management endpoints")
public class PlaceController {

    private final PlaceService placeService;

    PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Operation(
            summary = "Get all places",
            description = "Loads all places from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping
    List<PlaceEntity> loadAll() {
        return placeService.loadAll();
    }

    @Operation(
            summary = "Get one specific place",
            description = "Loads one specific place from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    PlaceEntity loadOne(@PathVariable UUID id) {
        return placeService.loadOne(id)
                .orElseThrow(() -> new BookingNotFoundException("Place with id " + id + " not found!"));
    }

    @Operation(
            summary = "Create a new place",
            description = "Creates a new place in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    PlaceEntity create(@RequestBody PlaceEntity entity) {
        return placeService.create(entity);
    }

    @Operation(
            summary = "Update an existing place",
            description = "Updates one specific and existing place in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    PlaceEntity update(@RequestBody PlaceEntity updatedEntity, @PathVariable UUID id) {
        return placeService.update(updatedEntity);
    }

    @Operation(
            summary = "Delete an existing place",
            description = "Deletes one specific and existing place in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        placeService.delete(id);
    }

}
