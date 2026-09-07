package com.weather.api;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.IngestPayload;
import com.weather.model.IngestResponse;
import com.weather.model.QueryRequest;
import com.weather.model.QueryResponse;
import com.weather.service.WeatherMetricService;

@RestController
@RequestMapping("/v1")
public class WaetherStationController implements DefaultApi {

    private final WeatherMetricService weatherMetricService;

    public WaetherStationController(WeatherMetricService weatherMetricService) {
        this.weatherMetricService = weatherMetricService;
    }

    @Override
    public ResponseEntity<QueryResponse> queryAnalytics(QueryRequest queryRequest) {
        // Execute dynamic aggregated query via service layer
        QueryResponse response = weatherMetricService.executeAnalyticsQuery(queryRequest);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<IngestResponse> ingestMetrics(IngestPayload ingestPayload) {
        // Delegate storage to the service layer
        int processedCount = weatherMetricService.processAndSaveMetrics(ingestPayload);

        // Build response matching OpenAPI specification
        IngestResponse response = new IngestResponse();
        response.setStatus("success");
        response.setMessage("Metrics recorded successfully");
        response.setStationId(ingestPayload.getStationId());
        response.setMetricsProcessed(processedCount);
        response.setProcessedAt(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
