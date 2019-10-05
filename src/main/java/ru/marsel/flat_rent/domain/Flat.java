package ru.marsel.flat_rent.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.AnnouncementInfo.class)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id")
    @JsonView(Views.AnnouncementInfo.class)
    private Address address;
    @JsonView(Views.AnnouncementInfo.class)
    private byte floor;
    @JsonView(Views.AnnouncementInfo.class)
    private byte totalFloors;
    @JsonView(Views.AnnouncementInfo.class)
    private int metricArea;
    @JsonView(Views.AnnouncementInfo.class)
    private byte roomsCount;
    @JsonView(Views.AnnouncementInfo.class)
    private int price;
}
