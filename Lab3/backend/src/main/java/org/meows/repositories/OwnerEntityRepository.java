package org.meows.repositories;

import org.meows.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerEntityRepository extends JpaRepository<OwnerEntity, Long> {
}
