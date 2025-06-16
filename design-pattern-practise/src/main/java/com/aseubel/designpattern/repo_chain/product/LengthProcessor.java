package com.aseubel.designpattern.repo_chain.product;

import com.aseubel.designpattern.repo_chain.Processor;
import com.aseubel.designpattern.repo_chain.ProcessorChain;
import com.aseubel.designpattern.repo_chain.Result;

import static com.aseubel.designpattern.repo_chain.product.Constant.MAX_PRODUCT_LENGTH;
import static com.aseubel.designpattern.repo_chain.product.Constant.MIN_PRODUCT_LENGTH;

/**
 * @author Aseubel
 * @date 2025/4/28 上午12:50
 */
public class LengthProcessor implements Processor<Product> {
    @Override
    public Result<Product> process(Product product, int index, ProcessorChain<Product> chain) {
        Integer length = product.getLength();
        if (length >= MIN_PRODUCT_LENGTH && length <= MAX_PRODUCT_LENGTH) {
            return chain.process(product, index);
        }
        return Result.fail(product, "长度不符合要求: " + length);
    }
}
