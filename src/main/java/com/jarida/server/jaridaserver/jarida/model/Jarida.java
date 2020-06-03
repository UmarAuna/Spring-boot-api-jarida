package com.jarida.server.jaridaserver.jarida.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jarida")
public class Jarida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Valid
    @NotEmpty(message = "Title is mandatory" )
    @Size(min = 2, message = "Title should have atleast 2 characters")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Valid
    @NotEmpty(message = "Content is mandatory" )
    @Size(min = 2, message = "Content should have atleast 2 characters")
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Valid
    @NotEmpty(message = "Author is mandatory" )
    @Size(min = 2, message = "Author should have atleast 2 characters")
    @Column(name = "author", nullable = false)
    private String author;

    public Jarida() {
    }

    public Jarida(String title, String content, String author) {
        super();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Jarida{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
