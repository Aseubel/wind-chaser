package com.aseubel.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aseubel
 * @date 2025/7/5 下午3:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;
    String name;
    int age;
}
