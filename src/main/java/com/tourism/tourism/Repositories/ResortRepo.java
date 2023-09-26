package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.Resorts;
import com.tourism.tourism.Entities.TouristPlaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResortRepo extends JpaRepository<Resorts,Long> {
    List<Resorts> findByTouristPlaces(TouristPlaces touristPlaces);
    List<Resorts> findByFeaturedTrue();
    List<Resorts> findByNameIs(String name);
}
