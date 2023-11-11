package org.softuni.repository;

import org.softuni.model.entities.UserActivationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationCodeRepository extends JpaRepository<UserActivationCodeEntity,Long> {
}
