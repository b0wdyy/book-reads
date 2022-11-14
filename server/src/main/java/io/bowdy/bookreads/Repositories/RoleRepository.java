package io.bowdy.bookreads.Repositories;

import io.bowdy.bookreads.Enums.ERole;
import io.bowdy.bookreads.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
