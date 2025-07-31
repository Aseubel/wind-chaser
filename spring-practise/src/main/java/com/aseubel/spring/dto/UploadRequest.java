package com.aseubel.spring.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Aseubel
 * @date 2025/7/31 下午5:15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadRequest implements Serializable {

    private MultipartFile file;

    private Long userId;
}
