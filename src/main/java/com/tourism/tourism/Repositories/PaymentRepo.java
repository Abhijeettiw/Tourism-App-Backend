package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.Payments;
import com.tourism.tourism.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payments,Long>
{
    List<Payments>  findByUser(User user);
}
