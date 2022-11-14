package io.bowdy.bookreads.Repositories;

import io.bowdy.bookreads.Models.User;
import io.bowdy.bookreads.Util.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest extends TestUtils {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        this.userRepository.deleteAll();
    }

    @Test
    void findByUsername() {
        // GIVEN
        User user = this.createUser();

        // WHEN
        this.userRepository.save(user);

        // THEN
        String expected = "username";
        assertThat(this.userRepository.findByUsername(expected)).isNotNull();
        assertThat(this.userRepository.findByUsername(expected).get().getUsername()).isEqualTo(expected);
    }

    @Test
    void existsByEmail() {
        // GIVEN
        User user = this.createUser();

        // WHEN
        this.userRepository.save(user);

        // THEN
        String expected = "email";
        assertThat(this.userRepository.existsByEmail(expected)).isTrue();
    }

    @Test
    void doesNotExistByEmail() {
        // GIVEN
        User user = this.createUser();

        // WHEN
        this.userRepository.save(user);

        // THEN
        String expected = "nomail";
        assertThat(this.userRepository.existsByEmail(expected)).isFalse();
    }

    @Test
    void existsByUsername() {
        // GIVEN
        User user = this.createUser();

        // WHEN
        this.userRepository.save(user);

        // THEN
        String expected = "username";
        assertThat(this.userRepository.existsByUsername(expected)).isTrue();
    }

    @Test
    void doesNotExistByUsername() {
        // GIVEN
        User user = this.createUser();

        // WHEN
        this.userRepository.save(user);

        // THEN
        String expected = "user";
        assertThat(this.userRepository.existsByUsername(expected)).isFalse();
    }

    private User createUser() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email");
        user.setPassword("password");
        return user;
    }

}