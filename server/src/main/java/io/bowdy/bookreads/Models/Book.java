package io.bowdy.bookreads.Models;

import io.bowdy.bookreads.Enums.Status;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID",
            strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "id",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "year", nullable = false)
    private String year;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "pages", nullable = false)
    private int pages;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "rating", nullable = false)
    private int rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return uuid != null && Objects.equals(uuid, book.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
