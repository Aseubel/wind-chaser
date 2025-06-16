package com.aseubel.designpattern.repo_chain.leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aseubel
 * @description 请假申请
 * @date 2025/4/28 下午1:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {
    /** 学生姓名 */
    private String name;
    /** 学生学号 */
    private String studentId;
    /** 请假类型 */
    private String type;
    /** 请假天数 */
    private int days;
    /** 请假理由 */
    private String reason;
}
