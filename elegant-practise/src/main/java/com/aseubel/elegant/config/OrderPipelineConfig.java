package com.aseubel.elegant.config;

import com.aseubel.elegant.filter.OrderLogSaveFilter;
import com.aseubel.elegant.filter.UserRiskFilter;
import com.aseubel.elegant.filter.UserValidFilter;
import com.aseubel.elegant.pipeline.EventFilter;
import com.aseubel.elegant.pipeline.FilterChainPipeline;
import com.aseubel.elegant.service.facade.IOrderPipelineFacadeService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aseubel
 * @date 2025/7/6 下午1:24
 */
@Configuration
public class OrderPipelineConfig {

    @Resource
    private IOrderPipelineFacadeService orderPipelineFacadeService;

    @Bean
    public FilterChainPipeline orderPipeline() {
        FilterChainPipeline<EventFilter> filterChainPipeline = new FilterChainPipeline<>();
        filterChainPipeline.addFirst(userValidFilter(), "用户合法性判断");
        filterChainPipeline.addFirst(userRiskFilter(), "用户风控判断");
        filterChainPipeline.addFirst(orderLogSaveFilter(), "下单操作日志存储");
        return filterChainPipeline;
    }

    @Bean
    public OrderLogSaveFilter orderLogSaveFilter() {
        return new OrderLogSaveFilter();
    }
    @Bean
    public UserRiskFilter userRiskFilter() {
        return new UserRiskFilter(orderPipelineFacadeService);
    }
    @Bean
    public UserValidFilter userValidFilter() {
        return new UserValidFilter(orderPipelineFacadeService);
    }
}
