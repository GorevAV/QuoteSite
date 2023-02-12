package com.example.quotesite.repository;

import com.example.quotesite.DTO.QuoteDTO;
import com.example.quotesite.domain.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepo extends JpaRepository<Quote,Long> {

    @Query("select new com.example.quotesite.DTO.QuoteDTO(q, count(ql)) from Quote q left join q.votes ql " +
            "group by q")
    Page<QuoteDTO> findTopTen(Pageable pageable);

    @Query("select new com.example.quotesite.DTO.QuoteDTO(q, count(ql)) from Quote q left join q.votes ql " +
            "group by q")
    Page<QuoteDTO> findFlopTen(Pageable pageable);
}
