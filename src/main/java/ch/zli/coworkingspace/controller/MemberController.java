package ch.zli.coworkingspace.controller;

import ch.zli.coworkingspace.exception.BookingNotFoundException;
import ch.zli.coworkingspace.model.MemberEntity;
import ch.zli.coworkingspace.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
@Tag(name = "Members", description = "Place management endpoints")
public class MemberController {

    private final MemberService memberService;

    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(
            summary = "Get all members",
            description = "Loads all members from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping
    List<MemberEntity> loadAll() {
        return memberService.loadAll();
    }

    @Operation(
            summary = "Get one specific member",
            description = "Loads one specific member from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    MemberEntity loadOne(@PathVariable UUID id) {
        return memberService.loadOne(id)
                .orElseThrow(() -> new BookingNotFoundException("Member with id " + id + " not found!"));
    }

    @Operation(
            summary = "Create a new member",
            description = "Creates a new member in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    MemberEntity create(@RequestBody MemberEntity entity) {
        return memberService.create(entity);
    }

    @Operation(
            summary = "Update an existing member",
            description = "Updates one specific and existing member in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    MemberEntity update(@RequestBody MemberEntity updatedEntity, @PathVariable UUID id) {
        return memberService.update(updatedEntity);
    }

    @Operation(
            summary = "Delete an existing member",
            description = "Deletes one specific and existing member in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        memberService.delete(id);
    }
}
