package ru.marsel.flat_rent.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.marsel.flat_rent.domain.Address;
import ru.marsel.flat_rent.domain.Announcement;
import ru.marsel.flat_rent.domain.Flat;
import ru.marsel.flat_rent.domain.User;
import ru.marsel.flat_rent.repo.AddressRepo;
import ru.marsel.flat_rent.repo.AnnouncementRepo;
import ru.marsel.flat_rent.repo.FlatRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementService {
    private final AnnouncementRepo announcementRepo;
    private final AddressRepo addressRepo;
    private final FlatRepo flatRepo;

    public AnnouncementService(AnnouncementRepo announcementRepo, AddressRepo addressRepo, FlatRepo flatRepo) {
        this.announcementRepo = announcementRepo;
        this.addressRepo = addressRepo;
        this.flatRepo = flatRepo;
    }

    public List<Announcement> findAnnouncements(String roomsCount, String userId) {
        if (!roomsCount.isEmpty()) {
            List<Announcement> announcements = new ArrayList<>();
            for (String s : roomsCount.split(",")) {
                announcements.addAll(announcementRepo.getAnnouncementsByFlat_RoomsCount(Byte.parseByte(s)));
            }
            return announcements;
        } else if (!userId.isEmpty()) {
            return announcementRepo.getAnnouncementsByUserId(Long.parseLong(userId));
        }

        return announcementRepo.findAll();
    }

    public Announcement create(Announcement announcement, User user) {
        if (user != null) {
            announcement.setUser(user);

            Flat flat = announcement.getFlat();
            Address address = flat.getAddress();
            Address addressFromDb = addressRepo.findAddressByDistrictAndStreetAndHouseNumber(address.getDistrict(), address.getStreet(), address.getHouseNumber());

            if (addressFromDb != null) {
                flat.setAddress(addressFromDb);
            } else {
                Address newAddress = addressRepo.save(address);
                flat.setAddress(newAddress);
            }

            announcement.setCreationDate(LocalDateTime.now());
            announcement.setFlat(flat);

            return announcementRepo.save(announcement);
        } else {
            return null;
        }
    }

    public Announcement update(Announcement announcementFromDb, Announcement announcement, User user) {
        if (user != null && user.equals(announcementFromDb.getUser())) {
            Address addressFromDb = announcementFromDb.getFlat().getAddress();
            Address address = announcement.getFlat().getAddress();
            BeanUtils.copyProperties(announcement, announcementFromDb, "id", "user", "flat", "creationDate");

            Flat flatFromDb = announcementFromDb.getFlat();
            Flat flat = announcement.getFlat();
            BeanUtils.copyProperties(flat, flatFromDb, "id", "address");
            announcementFromDb.setFlat(flatFromDb);

            if (!address.equals(addressFromDb)) {
                addressRepo.save(address);
                announcementFromDb.getFlat().setAddress(address);
            }

            Announcement savedAnnouncement = announcementRepo.save(announcementFromDb);
            checkAddress(addressFromDb);
            return savedAnnouncement;
        } else {
            return null;
        }
    }

    public boolean delete(Announcement announcement, User user) {
        if (announcement.getUser().equals(user)) {
            Address address = announcement.getFlat().getAddress();
            announcementRepo.delete(announcement);

            checkAddress(address);

            return true;
        } else {
            return false;
        }
    }

    private void checkAddress(Address address) {
        if (flatRepo.findFlatsByAddress_Id(address.getId()).isEmpty()) {
            addressRepo.delete(address);
        }
    }
}
