package com.aseubel.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Aseubel
 * @date 2025/8/20 下午8:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String userName;
    private String schoolCode;
    private String realName;
    private String phone;
    private Integer gender;
    private String avatar;
    private String signature;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
