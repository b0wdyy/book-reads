package io.bowdy.bookreads.Controllers;

import io.bowdy.bookreads.Enums.Status;
import io.bowdy.bookreads.Models.Book;
import io.bowdy.bookreads.Repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:4200")
public class BooksController {
    private final BookRepository bookRepository;
    private final String FOLDER_PATH = Paths.get("").toAbsolutePath().toString();

    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> index() {
        List<Book> books = this.bookRepository.findAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping()
    public ResponseEntity<Book> create(@RequestParam("title") String title,
                                       @RequestParam("author") String author,
                                       @RequestParam("genre") String genre,
                                       @RequestParam("year") String year,
                                       @RequestParam("publisher") String publisher,
                                       @RequestParam("isbn") String isbn,
                                       @RequestParam("pages") int pages,
                                       @RequestParam("status") Status status,
                                       @RequestParam("description") String description,
                                       @RequestParam("image") MultipartFile file) {
        Book book = new Book();
        String filePath = FOLDER_PATH + "/storage/" + file.getOriginalFilename();
        book.setImage(file.getOriginalFilename());
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setYear(year);
        book.setPublisher(publisher);
        book.setIsbn(isbn);
        book.setPages(pages);
        book.setStatus(status);
        book.setDescription(description);
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(201).body(this.bookRepository.save(book));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Book> getBookByUuid(@PathVariable("uuid") UUID uuid) {
        Book book = this.bookRepository.findByUuid(uuid);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("filename") String filename) throws IOException {
        String filepath = FOLDER_PATH + "/storage/" + filename;
        byte[] image = Files.readAllBytes(new File(filepath).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(image);
    }
}
