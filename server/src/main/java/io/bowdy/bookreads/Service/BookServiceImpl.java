package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.Enums.EStatus;
import io.bowdy.bookreads.Models.Book;
import io.bowdy.bookreads.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final String FOLDER_PATH = Paths.get("").toAbsolutePath().toString();
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book saveBook(String title, String author, String genre, String year, String publisher, String isbn, int pages, EStatus EStatus, String description, int rating, MultipartFile file) {
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
        book.setEStatus(EStatus);
        book.setDescription(description);
        book.setRating(rating);
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book, Long id) throws Exception {
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new Exception("Book not found");
        }
        Book bookToUpdate = optionalBook.get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setYear(book.getYear());
        bookToUpdate.setEStatus(book.getEStatus());
        bookToUpdate.setRating(book.getRating());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setPages(book.getPages());
        bookToUpdate.setGenre(book.getGenre());
        this.bookRepository.save(bookToUpdate);

        return bookToUpdate;
    }

    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book findOne(Long id) {
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return null;
        }
        return optionalBook.get();
    }

    @Override
    public void deleteAll() {
        this.bookRepository.deleteAllInBatch();
    }

    @Override
    public void batchInsert(List<Book> books) {
        this.bookRepository.saveAll(books);
    }
}
