package io.bowdy.bookreads.Models;

import com.sun.istack.NotNull;
import io.bowdy.bookreads.Enums.Status;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", unique = true)
    @NotNull
    private String title;
    @NotNull
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    @NotNull
    private String genre;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "year")
    @NotNull
    private String year;
    @Column(name = "isbn", unique = true)
    private String isbn;
    @Column(name = "pages")
    @NotNull
    private int pages;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    @NotNull
    private Status status;
    @Column(name = "rating")
    @NotNull
    private int rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
