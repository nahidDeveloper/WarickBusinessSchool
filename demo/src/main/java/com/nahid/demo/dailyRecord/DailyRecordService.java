package com.nahid.demo.dailyRecord;

import com.nahid.demo.metric.Metric;
import com.nahid.demo.metric.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DailyRecordService {

    @Autowired
    private DailyRecordRepository dailyRecordRepository;
    @Autowired
    private MetricRepository metricRepository;
    public List<DailyRecord> getAllDailyRecords(){
        return dailyRecordRepository.findAll();
    }

    public List<DailyRecord> getRecordsByDateAndMetric(String dateString, String metricName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateString);
            return dailyRecordRepository.findByDateAndMetricName(date, metricName);
        } catch (ParseException e) {
            // Handle parsing error
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

}
