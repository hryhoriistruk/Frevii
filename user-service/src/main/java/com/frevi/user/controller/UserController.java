package com.frevi.user.controller;

import com.frevi.user.dto.UserDto;
import com.frevi.user.request.UserRequest;
import com.frevi.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserRequest userRequest) throws Exception {
        UserDto user = userService.save(userRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UserDto>>> findAll(Pageable pageable,
                                                    PagedResourcesAssembler<UserDto> pagedResourcesAssembler) {

        Page<UserDto> users = userService.findAll(pageable);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(users));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
