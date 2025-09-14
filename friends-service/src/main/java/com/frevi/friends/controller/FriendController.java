package com.frevi.friends.controller;

import com.frevi.friends.dto.FriendRequest;
import com.frevi.friends.dto.FriendResponse;
import com.frevi.friends.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping
    public ResponseEntity<FriendResponse> createFriendRecord(@Valid @RequestBody FriendRequest friendRequest) {
        FriendResponse response = friendService.createFriend(friendRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<FriendResponse> findFriendById(@PathVariable long id) {
        return ResponseEntity.ok(friendService.findById(id));
    }

    @GetMapping("/by-id/{userId}")
    public ResponseEntity<List<Long>> findFriendsForUser(@PathVariable long userId) {
        return ResponseEntity.ok(friendService.findAllForUser(userId));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<FriendResponse>>> findFriends(Pageable pageable,
                                                                              PagedResourcesAssembler<FriendResponse> assembler) {
        Page<FriendResponse> friends = friendService.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(friends));
    }
}
