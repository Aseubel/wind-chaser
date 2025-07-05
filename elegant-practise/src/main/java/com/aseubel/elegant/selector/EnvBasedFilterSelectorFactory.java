package com.aseubel.elegant.selector;

import com.aseubel.elegant.config.OrderFilterConfigProperties;
import com.aseubel.elegant.order.BizLineEnum;
import com.aseubel.elegant.order.OrderRequest;
import com.aseubel.elegant.pipeline.selector.FilterSelector;
import com.aseubel.elegant.pipeline.selector.LocalListBasedFilterSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.aseubel.elegant.order.BizLineEnum.YW1;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:46
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EnvBasedFilterSelectorFactory implements OrderFilterSelectorFactory {

    private final OrderFilterConfigProperties properties;

    @Override
    public FilterSelector getFilterSelector(OrderRequest orderRequest) {
        BizLineEnum bizCode = orderRequest.getBizCode();
        if (YW1.equals(bizCode)) {
            List<String> filterNames = properties.getConfigs()
                    .getOrDefault(YW1.name(), Collections.unmodifiableList(new ArrayList<>()));
            return new LocalListBasedFilterSelector(filterNames);
        }
        return null;
    }
}
