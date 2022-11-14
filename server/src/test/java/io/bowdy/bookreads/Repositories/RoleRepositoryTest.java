package io.bowdy.bookreads.Repositories;

import io.bowdy.bookreads.Enums.ERole;
import io.bowdy.bookreads.Models.Role;
import io.bowdy.bookreads.Util.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoleRepositoryTest extends TestUtils {

    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        this.roleRepository.deleteAll();
    }

    @Test
    void findByName() {
        // GIVEN
        Role role = this.createRole();

        // WHEN
        this.roleRepository.save(role);

        // THEN
        ERole expected = ERole.ROLE_USER;
        assertThat(this.roleRepository.findByName(expected)).isNotNull();
        assertThat(this.roleRepository.findByName(expected).get().getName()).isEqualTo(expected);
    }

    private Role createRole() {
        return new Role(1L, ERole.ROLE_USER);
    }
}