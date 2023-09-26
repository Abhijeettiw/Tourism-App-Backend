package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdsRepo extends JpaRepository<Ads,Long> {

    List<Ads> findByNameIs(String name);
}
