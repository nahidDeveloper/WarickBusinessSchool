package com.nahid.demo.dailyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;
    @GetMapping()
    public ResponseEntity<List<DailyRecord>> getRecordsByDateAndMetric(
            @RequestParam("date") String date,
            @RequestParam("metric") String metricName) {
        List<DailyRecord> records = dailyRecordService.getRecordsByDateAndMetric(date, metricName);
        return ResponseEntity.ok(records);
    }

}
