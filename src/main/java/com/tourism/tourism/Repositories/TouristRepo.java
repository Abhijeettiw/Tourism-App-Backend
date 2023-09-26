package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.TouristPlaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristRepo extends JpaRepository<TouristPlaces,Long> {

    @Query("Select t from TouristPlaces t where t.name=:n")
    List<TouristPlaces> siteByName(@Param("n") String name);

}
