package com.jakegodsall.personalblog.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Posts", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;
    @Column
    private Date publishedDate;
    @Column
    private Date lastEditedDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    public Post() {}

    public Post(long id,
                String title,
                String description,
                String content,
                Date publishedDate,
                Date lastEditedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishedDate = publishedDate;
        this.lastEditedDate = lastEditedDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }
}
