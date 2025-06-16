package com.aseubel.designpattern.repo_chain;

import com.aseubel.designpattern.repo_chain.leave.CounselorProcessor;
import com.aseubel.designpattern.repo_chain.leave.DeanProcessor;
import com.aseubel.designpattern.repo_chain.leave.HeadTeacherProcessor;
import com.aseubel.designpattern.repo_chain.leave.Request;
import com.aseubel.designpattern.repo_chain.product.LengthProcessor;
import com.aseubel.designpattern.repo_chain.product.Product;
import com.aseubel.designpattern.repo_chain.product.WidthProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/4/28 上午12:48
 */
public class RepoChainTest {
    public static void main(String[] args) {
        handleLeave();
    }

    private static void handleProduct() {
        ProcessorChain<Product> chain = new ProcessorChain<>();
        chain.addProcessor(new LengthProcessor())
                .addProcessor(new WidthProcessor());

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, 10, 20));
        productList.add(new Product(2, 15, 25));
        productList.add(new Product(3, 20, 30));
        productList.add(new Product(4, 25, 35));
        productList.add(new Product(5, 30, 40));
        productList.add(new Product(6, 35, 45));
        productList.add(new Product(7, 40, 50));
        productList.add(new Product(8, 45, 55));
        productList.add(new Product(9, 50, 60));
        productList.add(new Product(10, 55, 65));

        for (Product product : productList) {
            Result<Product> result = chain.process(product);
            if (result.isSuccess()) {
                System.out.println(result.getData() + " 审核通过！");
            } else {
                System.out.println(result.getData() + " 审核不通过！原因：" + result.getMessage());
            }
        }
    }

    private static void handleLeave() {
        ProcessorChain<Request> chain = new ProcessorChain<>();
        chain.addProcessor(new HeadTeacherProcessor())
                .addProcessor(new CounselorProcessor())
                .addProcessor(new DeanProcessor());

        List<Request> requestList = new ArrayList<>();

        requestList.add(new Request("张三", "1001", "病假", 5, "身体不适"));
        requestList.add(new Request("李四", "1002", "事假", 1, "家庭原因"));
        requestList.add(new Request("王五", "1003", "年假", 3, "过年"));
        requestList.add(new Request("赵六", "1004", "病假", 7, "生病"));
        requestList.add(new Request("孙七", "1005", "事假", 2, "个人事务"));
        requestList.add(new Request("周八", "1006", "病假", 10, "长期疾病"));
        requestList.add(new Request("吴九", "1007", "年假", 15, "旅游"));
        requestList.add(new Request("郑十", "1008", "事假", 8, "朋友聚会"));
        requestList.add(new Request("钱十一", "1009", "病假", 12, "需要休息"));
        requestList.add(new Request("王十二", "1010", "年假", 21, "回家探亲"));

        requestList.forEach(request -> {
            Result<Request> result = chain.process(request);
            if (result.isSuccess()) {
                System.out.println(result.getData() + " 请假审批通过！");
            } else {
                System.out.println(result.getData() + " 请假审批不通过！原因：" + result.getMessage());
            }
        });
    }
}
