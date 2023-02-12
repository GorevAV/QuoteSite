package com.example.quotesite.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Please fill the text")
    private String text;
    @Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User author;

    @ManyToMany
    @JoinTable(
            name = "quote_votes",
            joinColumns = { @JoinColumn(name = "quote_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> votes = new HashSet<>();
    public Quote() {
    }
    public String getAuthorName() {
        return author != null ? author.getUserName() : "<none>";
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getVotes() {
        return votes;
    }

    public void setVotes(Set<User> votes) {
        this.votes = votes;
    }
}
