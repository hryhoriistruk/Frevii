package com.frevi.posts.controller;

import com.frevi.posts.dto.PostRequest;
import com.frevi.posts.dto.PostResponse;
import com.frevi.posts.service.PostService;
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
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.createPost(postRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(postResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable long id) throws Exception {
        PostResponse postResponse = postService.findById(id);

        return ResponseEntity.ok().body(postResponse);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<PostResponse>>> findAllPosts(Pageable pageable,
                                                                              PagedResourcesAssembler<PostResponse> assembler) {
        Page<PostResponse> posts = postService.findAll(pageable);

        return ResponseEntity.ok().body(assembler.toModel(posts));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PagedModel<EntityModel<PostResponse>>> findPostsByUserId(@PathVariable long userId,
                                                                                   Pageable pageable,
                                                                                   PagedResourcesAssembler<PostResponse> assembler) {
        Page<PostResponse> posts = postService.findByUserId(userId, pageable);

        return ResponseEntity.ok().body(assembler.toModel(posts));
    }
}
