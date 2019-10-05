package ru.marsel.flat_rent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.flat_rent.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
