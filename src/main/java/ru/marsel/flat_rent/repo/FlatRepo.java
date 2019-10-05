package ru.marsel.flat_rent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.flat_rent.domain.Flat;

import java.util.List;

public interface FlatRepo extends JpaRepository<Flat, Long> {
    List<Flat> findFlatsByAddress_Id(long addressId);
}
