package org.meows.repositories;

import org.meows.entities.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatEntityRepository extends JpaRepository<CatEntity, Long> {
}
