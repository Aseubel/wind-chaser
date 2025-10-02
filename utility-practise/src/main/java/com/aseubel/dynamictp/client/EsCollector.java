package com.aseubel.dynamictp.client;

import org.dromara.dynamictp.common.entity.ThreadPoolStats;
import org.dromara.dynamictp.common.util.JsonUtil;
import org.dromara.dynamictp.core.monitor.collector.AbstractCollector;

/**
 * @author Aseubel
 * @date 2025/9/13 下午11:51
 */
public class EsCollector extends AbstractCollector {

    private final EsClient esClient;

    public EsCollector() {
        this.esClient = new EsClient();
    }

    @Override
    public void collect(ThreadPoolStats poolStats) {
        esClient.save(JsonUtil.toJson(poolStats));
    }

    @Override
    public String type() {
        return "es";
    }
}
