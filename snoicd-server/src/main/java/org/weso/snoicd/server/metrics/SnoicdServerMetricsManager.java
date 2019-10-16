package org.weso.snoicd.server.metrics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SnoicdServerMetricsManager {

    private final static ConcurrentMap<String, Object> metricsMap = new ConcurrentHashMap<>();

    /**
     *
     * @return
     */
    public ConcurrentMap getMetrics() {
        return metricsMap;
    }

    /**
     *
     * @param metricKey
     * @return
     */
    public Object getMetric(String metricKey) {
        return metricsMap.get(metricKey);
    }

    /**
     *
     * @param metricKey
     * @param value
     */
    public void setMetric(String metricKey, Object value) {
        metricsMap.put(metricKey, value);
    }
}
