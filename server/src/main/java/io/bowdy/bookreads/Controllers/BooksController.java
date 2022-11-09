package io.bowdy.bookreads.Controllers;

import io.bowdy.bookreads.DTO.SuccessResponseDTO;
import io.bowdy.bookreads.Enums.Status;
import io.bowdy.bookreads.Models.Book;
import io.bowdy.bookreads.Service.BookServiceImpl;
import io.bowdy.bookreads.Util.ImageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BookServiceImpl bookService;

    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> index() {
        List<Book> books = this.bookService.getAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Book> create(@RequestParam("title") String title,
                                       @RequestParam("author") String author,
                                       @RequestParam("genre") String genre,
                                       @RequestParam("year") String year,
                                       @RequestParam("publisher") String publisher,
                                       @RequestParam("isbn") String isbn,
                                       @RequestParam("pages") int pages,
                                       @RequestParam("status") Status status,
                                       @RequestParam("description") String description,
                                       @RequestParam("rating") int rating,
                                       @RequestParam("image") MultipartFile file) {
        Book book = this.bookService.saveBook(title,
                author,
                genre,
                year,
                publisher,
                isbn,
                pages,
                status,
                description,
                rating,
                file);
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SuccessResponseDTO> updateBook(@PathVariable("uuid") UUID uuid, @RequestBody Book book) {
        try {
            Book updatedBook = this.bookService.updateBook(book, uuid);

            return ResponseEntity.ok(SuccessResponseDTO.builder().success(true).message("Successfully updated book with id " + updatedBook.getUuid()).build());
        } catch (Exception e) {
            return ResponseEntity.ok(SuccessResponseDTO.builder().success(false).message(e.getMessage()).build());
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Book> getBookByUuid(@PathVariable("uuid") UUID uuid) {
        Book book = this.bookService.findOne(uuid);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("filename") String filename) throws IOException {
        byte[] image = ImageUtil.getImageFromSystem(filename);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(image);
    }
}
