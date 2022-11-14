package io.bowdy.bookreads.Repositories;

import io.bowdy.bookreads.Enums.EStatus;
import io.bowdy.bookreads.Models.Book;
import io.bowdy.bookreads.Util.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryTest extends TestUtils {
    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void tearDown() {
        this.bookRepository.deleteAll();
    }

    @Test
    void itShouldGetAllBooks() {
        assertThat(this.bookRepository.findAll()).isNotNull();
        assertThat(this.bookRepository.findAll()).isEmpty();
        assertThat(this.bookRepository.findAll()).hasSize(0);
    }

    @Test
    void itShouldSaveBook() {
        // GIVEN
        Book book = new Book(1L, "title", "author", "genre", "publisher", "year", "422342123", 333, "description", "image", EStatus.READING, 4);

        // WHEN
        Book savedBook = this.bookRepository.save(book);

        // THEN
        String expected = "title";
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(expected);
    }

    @Test
    void itShouldUpdateBook() {
        // GIVEN
        Book book = new Book(1L, "title", "author", "genre", "publisher", "year", "422342123", 333, "description", "image", EStatus.READING, 4);
        Book savedBook = this.bookRepository.save(book);
        savedBook.setTitle("new title");

        // WHEN
        Book updatedBook = this.bookRepository.save(savedBook);

        // THEN
        String expected = "new title";
        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getTitle()).isEqualTo(expected);
    }
}