package com.example.quotesite.service;

import com.example.quotesite.DTO.QuoteDTO;
import com.example.quotesite.domain.Quote;
import com.example.quotesite.domain.User;
import com.example.quotesite.repository.QuoteRepo;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuoteService {

    private final EntityManager em;
    private final QuoteRepo quoteRepo;

    public QuoteService(EntityManager em, QuoteRepo quoteRepo) {
        this.em = em;
        this.quoteRepo = quoteRepo;
    }

    public List<Quote> getAllQuote() {
        return quoteRepo.findAll();
    }

    public Quote getQuoteById(Long quoteId) {
        return quoteRepo.findById(quoteId).orElseThrow();
    }


    @Transactional
    public Quote insert(User currentUser, Quote quote) {
        quote.setDateUpdate(LocalDateTime.now());
        quote.setAuthor(currentUser);
        return quoteRepo.save(quote);
    }

    @Transactional
    public void updateQuote(Long quoteId, Quote quote) {
        Quote updateQuote = getQuoteById(quoteId);
        updateQuote.setText(quote.getText());
        updateQuote.setDateUpdate(LocalDateTime.now());
        quoteRepo.save(updateQuote);
    }

    public void deleteQuote(Long quoteId) {
        quoteRepo.deleteById(quoteId);
    }

    public Page<QuoteDTO> getTopTen(Pageable pageable) {
        return quoteRepo.findTopTen(pageable);
    }

    public Page<QuoteDTO> getFlopTen(Pageable pageable) {
        return quoteRepo.findFlopTen(pageable);
    }

    public Quote getRandomQuote() {
        return em.createQuery("SELECT q FROM Quote q ORDER BY RANDOM() DESC LIMIT 1", Quote.class)
                .getSingleResult();
    }
}
