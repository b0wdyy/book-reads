package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.Enums.Status;
import io.bowdy.bookreads.Models.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface BookService {
    public List<Book> getAll();

    public Book saveBook(String title,
                         String author,
                         String genre,
                         String year,
                         String publisher,
                         String isbn,
                         int pages,
                         Status status,
                         String description,
                         int rating,
                         MultipartFile file);

    public Book updateBook(Book book, UUID id) throws Exception;

    public void deleteBook(UUID id);

    public Book findOne(UUID uuid);
}
