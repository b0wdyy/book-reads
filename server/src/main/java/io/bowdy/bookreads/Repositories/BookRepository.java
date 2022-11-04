package io.bowdy.bookreads.Repositories;

import io.bowdy.bookreads.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    public Book findByUuid(UUID uuid);
}
