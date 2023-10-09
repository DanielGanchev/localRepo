package org.softuni.repository;

import org.softuni.model.entities.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}
