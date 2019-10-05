package ru.marsel.flat_rent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.flat_rent.domain.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
    Address findAddressByDistrictAndStreetAndHouseNumber(String district, String street, int houseNumber);
}
