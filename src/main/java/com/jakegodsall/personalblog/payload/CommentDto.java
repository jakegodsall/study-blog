package com.jakegodsall.personalblog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 5, message = "Name must be at least 5 characters")
    private String name;
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Must be a valid email address")
    private String email;
    @NotEmpty(message = "Message cannot be empty")
    @Size(min = 20, message = "Message must be at least 20 characters")
    private String body;

    public CommentDto() {
    }

    public CommentDto(long id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
