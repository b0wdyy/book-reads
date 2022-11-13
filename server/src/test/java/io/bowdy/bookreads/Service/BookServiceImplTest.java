package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.Enums.Status;
import io.bowdy.bookreads.Models.Book;
import io.bowdy.bookreads.Util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookServiceImplTest extends TestUtils {
    private final BookServiceImpl bookService;
    private final Book BOOK_DATA = new Book(3L, "The Bodhi of The Rings", "J.R.R. Tolkien", "Fantasy", "Allen & Unwin", "1937", "978100626", 310, "Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. But his contentment is disturbed when the wizard, Gandalf, and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure. They have launched a plot to raid the treasure hoard guarded by Smaug the Magnificent, a large and very dangerous dragon. Bilbo reluctantly joins their quest, unaware that on his journey to the Lonely Mountain he will encounter both a magic ring and a frightening creature known as Gollum.", "https://images-na.ssl-images-amazon.com/images/I/51Z9Z9ZQZEL._SX331_BO1,204,203,200_.jpg", Status.READING, 5);
    private final List<Book> BOOK_DATA_LIST = List.of(
            new Book(1L, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "Allen & Unwin", "1937", "978-0-618-10062-6", 310, "Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. But his contentment is disturbed when the wizard, Gandalf, and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure. They have launched a plot to raid the treasure hoard guarded by Smaug the Magnificent, a large and very dangerous dragon. Bilbo reluctantly joins their quest, unaware that on his journey to the Lonely Mountain he will encounter both a magic ring and a frightening creature known as Gollum.", "https://images-na.ssl-images-amazon.com/images/I/51Z9Z9ZQZEL._SX331_BO1,204,203,200_.jpg", Status.READING, 5),
            new Book(2L, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", "Allen & Unwin", "1944", "978-0-618-6", 1216, "The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien. The story began as a sequel to Tolkien's 1937 fantasy novel The Hobbit, but eventually developed into a much larger work. Written in stages between 1937 and 1949, The Lord of the Rings is one of the best-selling novels ever written, with over 150 million copies sold.", "https://images-na.ssl-images-amazon.com/images/I/51Z9Z9ZQZEL._SX331_BO1,204,203,200_.jpg", Status.READ, 4),
            new Book(BOOK_DATA.getId(), BOOK_DATA.getTitle(), BOOK_DATA.getAuthor(), BOOK_DATA.getGenre(), BOOK_DATA.getPublisher(), BOOK_DATA.getYear(), BOOK_DATA.getIsbn(), BOOK_DATA.getPages(), BOOK_DATA.getDescription(), BOOK_DATA.getImage(), BOOK_DATA.getStatus(), BOOK_DATA.getRating())
    );

    @Autowired
    BookServiceImplTest(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @Test
    void getAll() {
        this.bookService.batchInsert(BOOK_DATA_LIST);

        assertThat(this.bookService.getAll()).isNotNull();
        assertThat(this.bookService.getAll()).hasSize(BOOK_DATA_LIST.size());
        assertThat(this.bookService.getAll()).last().isEqualTo(BOOK_DATA_LIST.get(BOOK_DATA_LIST.size() - 1));
    }

    @Test
    void deleteBook() {
        this.bookService.batchInsert(BOOK_DATA_LIST);

        assertThat(this.bookService.getAll()).hasSize(BOOK_DATA_LIST.size());
        this.bookService.deleteBook(1L);
        assertThat(this.bookService.getAll()).hasSize(BOOK_DATA_LIST.size() - 1);
    }

    @Test
    void findOne() {
        this.bookService.batchInsert(BOOK_DATA_LIST);
        Book book = this.bookService.findOne(BOOK_DATA.getId());
        assertThat(book).isNotNull();
        assertThat(book.getIsbn()).isEqualTo(BOOK_DATA.getIsbn());
        assertThat(book).isEqualTo(BOOK_DATA);
    }

    @Test
    void updateBook() {
    }
}