package ru.marsel.flat_rent.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.marsel.flat_rent.domain.User;
import ru.marsel.flat_rent.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean createUser(User user) {
        User userFromDb = userRepo.findUserByEmail(user.getEmail());

        if (userFromDb != null) {
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return true;
        }
    }

    public User updateUser(User userFromDb, User user, User currentUser) {
        User userByEmail = userRepo.findUserByEmail(user.getEmail());

        if (currentUser.equals(userFromDb) && (userByEmail == null || userFromDb.equals(userByEmail))) {
            BeanUtils.copyProperties(user, userFromDb, "id", "password");
            BeanUtils.copyProperties(user, currentUser, "id", "password");

            return userRepo.save(userFromDb);
        } else {
            return null;
        }
    }
}
