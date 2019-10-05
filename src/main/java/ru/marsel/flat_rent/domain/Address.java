package ru.marsel.flat_rent.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.AnnouncementInfo.class)
    private Long id;

    @JsonView(Views.AnnouncementInfo.class)
    private String district;
    @JsonView(Views.AnnouncementInfo.class)
    private String street;

    @JsonView(Views.AnnouncementInfo.class)
    private int houseNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                district.equals(address.district) &&
                street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district, street, houseNumber);
    }
}
