package com.nahid.demo.dailyRecord;

import com.nahid.demo.metric.Metric;
import com.nahid.demo.metric.MetricRepository;
import com.nahid.demo.team.Team;
import com.nahid.demo.team.TeamRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DailyRecordDataLoader {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private DailyRecordRepository dailyRecordRepository;

    @PostConstruct
    public void loadDailyRecordsFromFile() {
        try {
            File directory = new ClassPathResource("data").getFile();
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    String dateStr = fileName.substring(0, fileName.indexOf('.'));
                    Date date = parseDate(dateStr);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        Map<String, List<Map<String, String>>> jsonData = objectMapper.readValue(file, Map.class);
                        for (String metricName : jsonData.keySet()) {
                            Optional<Metric> metric = metricRepository.findByName(metricName);
                            if (metric.isEmpty()) {
                                // Handle metric not found
                                continue;
                            }

                            List<Map<String, String>> entries = jsonData.get(metricName);
                            for (Map<String, String> entry : entries) {
                                int teamId = Integer.parseInt(String.valueOf(entry.get("team")));


                                Optional<Team> teamOptional = teamRepository.findById(teamId);
                                if (teamOptional.isEmpty()) {
                                    // Handle team not found
                                    continue;
                                }
                                Team team = teamOptional.get();

                                String valueStr = entry.get("value");
                                double value = Double.parseDouble(valueStr);

                                DailyRecord dailyRecord = new DailyRecord();
                                dailyRecord.setTeam(team);
                                dailyRecord.setMetric(metric.get());
                                dailyRecord.setValue(value);
                                dailyRecord.setDate(date);
                                dailyRecordRepository.save(dailyRecord);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
