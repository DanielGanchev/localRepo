package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {




    @Query(nativeQuery = true, value = "SELECT * FROM offers WHERE user_id IS NULL")
    List<Offer> getAllAvailable();


    List<Offer> findAllByCreator(User user);

    List<Offer> findAllByUser(User user);
}
