package com.aseubel.designpattern.company;

/**
 * @author Aseubel
 * @date 2025/6/18 下午5:41
 */
public enum Major {
    FRONT_END_DEVELOPMENT("前端开发"),
    BACK_END_DEVELOPMENT("后端开发"),
    FULL_STACK_DEVELOPMENT("全栈开发"),
    PRODUCT_DESIGN("产品设计"),
    DATA_ANALYSIS("数据分析"),
    QUALITY_ASSURANCE("测试工程师"),
    OPERATIONS_SUPPORT("运维工程师");

    private final String displayName;

    Major(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
