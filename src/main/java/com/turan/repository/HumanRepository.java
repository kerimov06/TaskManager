package com.turan.repository;

import com.turan.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumanRepository extends JpaRepository<Human,Long> {
    @Query(value = "from Human where username = :username")
    Optional<Human> findByUsername(String username);
}
