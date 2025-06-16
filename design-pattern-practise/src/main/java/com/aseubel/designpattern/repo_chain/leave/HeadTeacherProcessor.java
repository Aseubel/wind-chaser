package com.aseubel.designpattern.repo_chain.leave;

import com.aseubel.designpattern.repo_chain.Processor;
import com.aseubel.designpattern.repo_chain.ProcessorChain;
import com.aseubel.designpattern.repo_chain.Result;

/**
 * @author Aseubel
 * @description 班主任审批处理器
 * @date 2025/4/28 下午1:56
 */
public class HeadTeacherProcessor implements Processor<Request> {
    @Override
    public Result<Request> process(Request data, int index, ProcessorChain<Request> chain) {
        int days = data.getDays();
        int random = (int) (Math.random() * 10) + 1;
        // 80% chance to pass, 20% chance to fail
        if (days > 3) {
            return chain.process(data, index);
        }
        return random <= 8 ? Result.success(data) : Result.fail(data, "班主任审批不通过！");
    }
}
