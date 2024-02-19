package com.nahid.demo;

import com.nahid.demo.metric.Metric;
import com.nahid.demo.metric.MetricRepository;
import com.nahid.demo.metric.MetricService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MetricServiceTest {

    @Mock
    private MetricRepository metricRepository;

    @InjectMocks
    private MetricService metricService;

    @Test
    public void testGetAllMetrics() {
        List<Metric> mockMetrics = new ArrayList<>();
        mockMetrics.add(new Metric());
        mockMetrics.add(new Metric());

        when(metricRepository.findAll()).thenReturn(mockMetrics);

        List<Metric> metrics = metricService.getAllMetrics();
        assertEquals(2, metrics.size());
    }
}
