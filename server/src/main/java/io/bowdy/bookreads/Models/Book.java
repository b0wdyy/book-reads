package io.bowdy.bookreads.Models;

import com.sun.istack.NotNull;
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
@AllArgsConstructor
@NoArgsConstructor
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
        return uuid != null && Objects.equals(uuid, book.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
