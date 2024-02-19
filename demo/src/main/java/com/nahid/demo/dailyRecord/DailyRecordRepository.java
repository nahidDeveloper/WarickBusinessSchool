package com.nahid.demo.dailyRecord;

import com.nahid.demo.metric.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DailyRecordRepository  extends JpaRepository<DailyRecord,Integer> {
    List<DailyRecord> findByDateAndMetricName(Date date, String metricName);
}
