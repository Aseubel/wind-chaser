package com.aseubel.designpattern.repo_chain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aseubel
 * @description 责任链处理的产品类
 * @date 2025/4/28 上午12:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    /** 产品ID */
    private Integer id;

    /** 长度 */
    private Integer length;

    /** 宽度 */
    private Integer width;

}
