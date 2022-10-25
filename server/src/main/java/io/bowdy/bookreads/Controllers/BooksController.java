package io.bowdy.bookreads.Controllers;

import io.bowdy.bookreads.Models.Book;
import io.bowdy.bookreads.Repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BookRepository bookRepository;

    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> index() {
        List<Book> books = this.bookRepository.findAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping()
    public Book create(@RequestBody Book book) {
        return this.bookRepository.save(book);
    }
}
