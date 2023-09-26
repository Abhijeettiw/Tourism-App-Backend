package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.ResortNearbyPlaces;
import com.tourism.tourism.Entities.Resorts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResortNearbyPlacesRepo extends JpaRepository<ResortNearbyPlaces,Long> {
    List<ResortNearbyPlaces> findByResorts(Resorts resorts);
    @Query("Select r from ResortNearbyPlaces r where r.name=:n")
    List<ResortNearbyPlaces> nbpByName(@Param("n") String name);
}
