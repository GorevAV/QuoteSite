package com.example.quotesite.controller;

import com.example.quotesite.DTO.QuoteDTO;
import com.example.quotesite.domain.Quote;
import com.example.quotesite.domain.User;
import com.example.quotesite.service.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/random-quote")
    public Quote randomQuote() {
        return quoteService.getRandomQuote();
    }
    @GetMapping
    public List<Quote> getAllQuote() {
        return quoteService.getAllQuote();
    }

    @GetMapping("/top-ten")
    public Page<QuoteDTO> getTopTen (
            @PageableDefault(sort = { "votes" }, direction = Sort.Direction.DESC) Pageable pageable
    ){
        return quoteService.getTopTen(pageable);
    }
    @GetMapping("/floap-ten")
    public Page<QuoteDTO> getFlopTen (
            @PageableDefault(sort = { "votes" }, direction = Sort.Direction.ASC) Pageable pageable
    ){
        return quoteService.getFlopTen(pageable);
    }

    @GetMapping({"/{quoteId}"})
    public Quote getQuote(@PathVariable Long quoteId) {
        return quoteService.getQuoteById(quoteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quote saveQuote(@AuthenticationPrincipal User currentUser, @RequestBody Quote quote) {
        return quoteService.insert(currentUser, quote);
    }

    @PutMapping({"/{quoteId}"})
    public void updateQuote(@PathVariable("quoteId") Long quoteId, @RequestBody Quote quote) {
        quoteService.updateQuote(quoteId, quote);
    }

    @DeleteMapping({"/{deleteId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable("deleteId") Long quoteId) {
        quoteService.deleteQuote(quoteId);
    }

    @GetMapping("/{quote}/score")
    public void score(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Quote quote
            ) {
        Set<User> votes = quote.getVotes();

        if (votes.contains(currentUser)) {
            votes.remove(currentUser);
        } else {
            votes.add(currentUser);
        }
    }
}
