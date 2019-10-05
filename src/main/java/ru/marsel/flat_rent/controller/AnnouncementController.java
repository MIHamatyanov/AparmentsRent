package ru.marsel.flat_rent.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.marsel.flat_rent.domain.Announcement;
import ru.marsel.flat_rent.domain.User;
import ru.marsel.flat_rent.domain.Views;
import ru.marsel.flat_rent.service.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    @JsonView(Views.AnnouncementInfo.class)
    public List<Announcement> getListOfAnnouncements(
            @RequestParam(required = false, defaultValue = "") String roomsCount,
            @RequestParam(required = false, defaultValue = "") String userId
    ) {
        return announcementService.findAnnouncements(roomsCount, userId);
    }

    @GetMapping("{id}")
    @JsonView(Views.AnnouncementInfo.class)
    public Announcement getOneAnnouncement(@PathVariable("id") Announcement announcement) {
        return announcement;
    }

    @PostMapping
    @JsonView(Views.AnnouncementInfo.class)
    public Announcement create(
            @RequestBody Announcement announcement,
            @AuthenticationPrincipal User user
    ) {
        return announcementService.create(announcement, user);
    }

    @PutMapping("{id}")
    @JsonView(Views.AnnouncementInfo.class)
    public Announcement update(
        @PathVariable("id") Announcement announcementFromDb,
        @RequestBody Announcement announcement,
        @AuthenticationPrincipal User user
    ) {
        return announcementService.update(announcementFromDb, announcement, user);
    }

    @DeleteMapping("{id}")
    public boolean delete(
            @PathVariable("id") Announcement announcement,
            @AuthenticationPrincipal User user
    ) {
        return announcementService.delete(announcement, user);
    }


}
