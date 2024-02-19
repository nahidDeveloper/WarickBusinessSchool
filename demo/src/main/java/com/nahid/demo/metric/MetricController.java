package com.nahid.demo.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metric")
public class MetricController {
    @Autowired
    private MetricService metricService;

    @GetMapping
    public ResponseEntity<List<Metric>>getAllMetrics(){
        return new ResponseEntity<List<Metric>>(metricService.getAllMetrics(), HttpStatus.OK);
    }
}
