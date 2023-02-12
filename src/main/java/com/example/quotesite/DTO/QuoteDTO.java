package com.example.quotesite.DTO;

import com.example.quotesite.domain.Quote;
import com.example.quotesite.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class QuoteDTO {

    private Long id;
    private String text;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateUpdate;
    private User author;
    private Long votes;


    public QuoteDTO(Quote quote, Long votes) {
        this.id = quote.getId();
        this.text = quote.getText();
        this.dateUpdate = quote.getDateUpdate();
        this.author = quote.getAuthor();
        this.votes = votes;
    }

    public String getAuthorName() {
        return author != null ? author.getUserName() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public User getAuthor() {
        return author;
    }

    public Long getVotes() {
        return votes;
    }

}
