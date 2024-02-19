package com.nahid.demo.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MetricDataLoader implements CommandLineRunner {
    @Autowired
    private MetricRepository metricRepository;
    @Override
    public void run(String... args) throws Exception {
        String[][] metricData ={
                {"wacc","%"},
                {"scores" ,""},
                {"factory_utilization" , "%"},
                {"employee_engagement" , "%"},
                {"interest_coverage" , "x"},
                {"marketing_spend_rev" , " USD"},
                {"e_cars_sales" , " units"},
                {"co2_penalty" , "M (USD)"}
            };
        for(String[]data:metricData) {
            Metric metric = new Metric();
            metric.setName(data[0]);
            metric.setMeasurement(data[1]);
            metricRepository.save(metric);
        }

    }
}
