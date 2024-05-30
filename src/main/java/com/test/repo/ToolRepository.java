package com.test.repo;

import com.test.domain.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, Long> {

    @Query("SELECT t FROM ToolEntity t WHERE t.toolCode = ?1")
    ToolEntity findByToolCode(String toolCode);
}
