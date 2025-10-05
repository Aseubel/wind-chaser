package com.aseubel.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/10/5 下午12:51
 */
public class TableTests {
    public static void main(String[] args) {
        Table<String, String, Integer> salaryTable = HashBasedTable.create();

        salaryTable.put("开发部", "1月", 12000);
        salaryTable.put("开发部", "2月", 13000);
        salaryTable.put("市场部", "1月", 10000);

        // 获取开发部2月工资
        Integer salary = salaryTable.get("开发部", "2月");
        System.out.println("工资：" + salary);

        // 获取开发部所有月份数据
        Map<String, Integer> devDept = salaryTable.row("开发部");
        System.out.println("开发部所有月份工资：" + devDept);
    }
}
