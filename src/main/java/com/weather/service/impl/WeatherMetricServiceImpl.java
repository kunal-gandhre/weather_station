package com.weather.service.impl;

import org.springframework.stereotype.Service;

import com.weather.model.IngestPayload;
import com.weather.model.QueryRequest;
import com.weather.model.QueryResponse;
import com.weather.service.WeatherMetricService;

@Service 
public class WeatherMetricServiceImpl implements WeatherMetricService {

    @Override
    public int processAndSaveMetrics(IngestPayload payload) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processAndSaveMetrics'");
    }

    @Override
    public QueryResponse executeAnalyticsQuery(QueryRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeAnalyticsQuery'");
    }
}
