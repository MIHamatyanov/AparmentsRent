package ru.marsel.flat_rent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.flat_rent.domain.Announcement;

import java.util.List;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {
    List<Announcement> getAnnouncementsByFlat_RoomsCount(byte roomsCount);

    List<Announcement> getAnnouncementsByUserId(Long userId);
}
