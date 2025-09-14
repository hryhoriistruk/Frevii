package com.frevi.announcement.controller;

import com.frevi.announcement.dto.AnnouncementRequest;
import com.frevi.announcement.dto.AnnouncementResponse;
import com.frevi.announcement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public ResponseEntity<AnnouncementResponse> create(AnnouncementRequest announcementRequest) {
        AnnouncementResponse announcementResponse = announcementService.create(announcementRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(announcementResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(announcementResponse);
    }

    public ResponseEntity<AnnouncementResponse> findById(long id) {
        AnnouncementResponse announcementResponse = announcementService.findById(id);

        return ResponseEntity.ok(announcementResponse);
    }

    public ResponseEntity<PagedModel<EntityModel<AnnouncementResponse>>> findAll(Pageable pageable,
                                                                                 PagedResourcesAssembler<AnnouncementResponse> assembler) {
        Page<AnnouncementResponse> page = announcementService.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(page));
    }

    public ResponseEntity<Void> deleteById(long id) {
        announcementService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
