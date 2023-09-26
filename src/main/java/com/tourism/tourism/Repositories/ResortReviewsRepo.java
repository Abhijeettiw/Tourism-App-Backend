package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.ResortReviews;
import com.tourism.tourism.Entities.Resorts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResortReviewsRepo extends JpaRepository<ResortReviews,Long> {
    List<ResortReviews> findByResorts(Resorts resorts);
}
