package com.weather.service;

import com.weather.model.IngestPayload;
import com.weather.model.QueryRequest;
import com.weather.model.QueryResponse;

/**
 * 
 */

public interface WeatherMetricService {
    
    /**
     * Parses incoming batch payload and saves time-series readings to database.
     * @return Number of metric items processed.
     */
    int processAndSaveMetrics(IngestPayload payload);

    /**
     * Executes aggregated SQL/TimescaleDB queries per metric.
     */
    QueryResponse executeAnalyticsQuery(QueryRequest request);
}
