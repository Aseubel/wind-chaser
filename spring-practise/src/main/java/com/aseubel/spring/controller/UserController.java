package com.aseubel.spring.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.aseubel.spring.dto.UploadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @author Aseubel
 * @date 2025/7/31 下午2:30
 */
@RestController
@Slf4j
public class UserController {

    private String uploadPath = "D:\\develop\\Java\\javacode\\wind-chaser\\spring-practise\\src\\main\\resources\\static\\";

    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    @GetMapping(value = "/avatar/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable String imgName) {
        try {
            Resource resource = new ClassPathResource("/static/" + imgName);
            HttpStatus status = HttpStatus.OK;
            // 判断图片是否存在
            if (!resource.exists()) {
                resource = new ClassPathResource("/static/default.jpg");
                status = HttpStatus.NOT_FOUND;
            }
            // 读取图片文件
            InputStream is = resource.getInputStream();
            byte[] avatarBytes = StreamUtils.copyToByteArray(is);
            return new ResponseEntity<>(avatarBytes, status);
        } catch (IOException e) {
            // 使用日志框架记录错误信息
            System.err.println("Error reading avatar file: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@ModelAttribute UploadRequest request) {
        MultipartFile file = request.getFile();
        try {
            byte[] bytes = file.getBytes();

            // 生成随机UUID作为文件名
            String uuidFileName = IdUtil.randomUUID() + "." + FileUtil.extName(file.getOriginalFilename());

            // 构建目标文件路径
            String targetFilePath = uploadPath + uuidFileName;

            // 使用Hutool的IoUtil工具类将文件写入目标路径
            IoUtil.write(new FileOutputStream(targetFilePath), true, bytes);

            // 返回生成的UUID文件名以便客户端可以使用
            return new ResponseEntity<>("http://localhost:8080/avatar/" + uuidFileName, HttpStatus.OK);
        } catch (IOException e) {
            // 使用日志框架记录错误信息
            System.err.println("Error writing file: " + e.getMessage());
            return new ResponseEntity<>("Upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/upload")
//    public Mono<ResponseEntity<String>> upload(@RequestPart("file") Mono<FilePart> filePart,
//                                               @RequestPart("userId") Mono<String> userId) {
//        return filePart
//                .zipWith(userId)
//                .flatMap(tuple -> {
//                    FilePart file = tuple.getT1();
//                    String userIdValue = tuple.getT2();
//
//                    String uuid = IdUtil.fastSimpleUUID();
//                    String extension = file.filename().substring(file.filename().lastIndexOf('.'));
//                    String fileName = uuid + extension;
//                    String targetPath = Paths.get("static", fileName).toString();
//
//                    return file.transferTo(new File(targetPath))
//                            .thenReturn(fileName);
//                })
//                .map(fileName ->
//                        new ResponseEntity<>("http://localhost:8080/resources/" + fileName, HttpStatus.OK))
//                .onErrorResume(e -> {
//                        log.error("Error writing file: {}", e.getMessage());
//                        return Mono.just(new ResponseEntity<>("Upload failed: " + e.getMessage(),
//                                HttpStatus.INTERNAL_SERVER_ERROR));
//                    });
//    }
}
