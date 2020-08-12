package com.themonk.todo.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

    public static int  userIdCounter = 0;

    static {
        inMemoryUserList.add(new JwtUserDetails((long) ++userIdCounter, "leapdemo",
                "$2a$10$pTFL3sAS9F4ptXTj1Oh/OO4ApEfH0WMmIqe7WcHACawAfV0Ii.SWa", "ROLE_USER_2"));
    }

    public void createUser(String username, String password) {
        System.out.println("username "+username);
        System.out.println("Password "+password);
        inMemoryUserList.add(new JwtUserDetails((long) ++userIdCounter, username,
                passwordGenerator(password), "ROLE_USER_2"));
    }

    public static String passwordGenerator(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedString = encoder.encode(password);
        return encodedString;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }


}
