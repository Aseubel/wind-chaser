package com.aseubel.designpattern.repo_chain.leave;

import com.aseubel.designpattern.repo_chain.Processor;
import com.aseubel.designpattern.repo_chain.ProcessorChain;
import com.aseubel.designpattern.repo_chain.Result;

/**
 * @author Aseubel
 * @description 辅导员审批处理器
 * @date 2025/4/28 下午1:56
 */
public class DeanProcessor implements Processor<Request> {
    @Override
    public Result<Request> process(Request data, int index, ProcessorChain<Request> chain) {
        int days = data.getDays();
        int random = (int) (Math.random() * 10) + 1;
        // 60% chance to pass, 40% chance to fail
        if (days > 14) {
            return chain.process(data, index);
        }
        return random <= 6 ? Result.success(data) : Result.fail(data, "班主任审批不通过！");
    }
}
