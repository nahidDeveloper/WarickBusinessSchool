package com.nahid.demo.metric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetricRepository extends JpaRepository<Metric,Integer> {
    Optional<Metric> findByName(String name);
}
