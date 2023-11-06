package com.example.restfulpolygon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyOpenCloseRepository extends JpaRepository<DailyOpenClose, Long> {
    // Repository queries if needed
}
