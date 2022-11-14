package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.Enums.EStatus;
import io.bowdy.bookreads.Models.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book saveBook(String title,
                  String author,
                  String genre,
                  String year,
                  String publisher,
                  String isbn,
                  int pages,
                  EStatus EStatus,
                  String description,
                  int rating,
                  MultipartFile file);

    Book updateBook(Book book, Long id) throws Exception;

    void deleteBook(Long id);

    Book findOne(Long id);

    void deleteAll();

    void batchInsert(List<Book> books);
}
