package com.aseubel.designpattern.repo_chain.product;

import com.aseubel.designpattern.repo_chain.Processor;
import com.aseubel.designpattern.repo_chain.ProcessorChain;
import com.aseubel.designpattern.repo_chain.Result;

import static com.aseubel.designpattern.repo_chain.product.Constant.MAX_PRODUCT_WIDTH;
import static com.aseubel.designpattern.repo_chain.product.Constant.MIN_PRODUCT_WIDTH;

/**
 * @author Aseubel
 * @date 2025/4/28 上午12:50
 */
public class WidthProcessor implements Processor<Product> {
    @Override
    public Result<Product> process(Product product, int index, ProcessorChain<Product> chain) {
        Integer width = product.getWidth();
        if (width >= MIN_PRODUCT_WIDTH && width <= MAX_PRODUCT_WIDTH) {
            return chain.process(product, index);
        }
        return Result.fail(product, "宽度不符合要求: " + width);
    }
}
