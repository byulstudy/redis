package com.byultudy.redisstudy.concert;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert , Long> {
}
