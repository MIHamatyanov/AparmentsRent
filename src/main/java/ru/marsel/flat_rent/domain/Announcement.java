package ru.marsel.flat_rent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.AnnouncementInfo.class)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.AnnouncementInfo.class)
    private LocalDateTime creationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonView(Views.AnnouncementInfo.class)
    private User user;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "flat_id")
    @JsonView(Views.AnnouncementInfo.class)
    private Flat flat;

    @JsonView(Views.AnnouncementInfo.class)
    private String description;
}
