package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.ResortFeatures;
import com.tourism.tourism.Entities.Resorts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResortFeaturesRepo extends JpaRepository<ResortFeatures,Long> {
    List<ResortFeatures> findByResorts(Resorts resorts);
}
