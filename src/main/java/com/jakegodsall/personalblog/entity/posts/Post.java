package com.jakegodsall.personalblog.entity.posts;

import com.jakegodsall.personalblog.entity.posts.CodeComprehensionExercise;
import com.jakegodsall.personalblog.entity.posts.CodingExercise;
import com.jakegodsall.personalblog.entity.posts.ConceptualQuestion;
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
    private String blogPost;
    @Column
    private Date publishedDate;
    @Column
    private Date lastEditedDate;
    @Column(unique = true)
    private String slug;
    @Column(unique = true)
    private String youtubeURL;
    private String category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConceptualQuestion> conceptualQuestions = new HashSet<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CodeComprehensionExercise> codeComprehensionExercises = new HashSet<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CodingExercise> codingExercises = new HashSet<>();

    public Post() {}

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

    public String getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(String blogPost) {
        this.blogPost = blogPost;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<ConceptualQuestion> getConceptualQuestions() {
        return conceptualQuestions;
    }

    public void setConceptualQuestions(Set<ConceptualQuestion> conceptualQuestions) {
        this.conceptualQuestions = conceptualQuestions;
    }

    public Set<CodeComprehensionExercise> getCodeComprehensionExercises() {
        return codeComprehensionExercises;
    }

    public void setCodeComprehensionExercises(Set<CodeComprehensionExercise> codeComprehensionExercises) {
        this.codeComprehensionExercises = codeComprehensionExercises;
    }

    public Set<CodingExercise> getCodingExercises() {
        return codingExercises;
    }

    public void setCodingExercises(Set<CodingExercise> codingExercises) {
        this.codingExercises = codingExercises;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title + "}";
    }
}
