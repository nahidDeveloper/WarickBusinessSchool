package com.nahid.demo.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {
    @Autowired
    private MetricRepository metricRepository;

    public List<Metric> getAllMetrics(){
         return metricRepository.findAll();
    }
}
