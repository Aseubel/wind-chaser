package com.aseubel.dynamictp.client;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aseubel
 * @date 2025/9/13 下午11:51
 */
@Slf4j
public class EsClient {

    public void save(String json) {
        log.info("save to es, json: {}", json);
    }
}
