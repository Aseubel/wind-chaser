package com.aseubel.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Aseubel
 * @date 2025/9/12 下午1:20
 */
public class FileReader {
    
    private static final Path path = Paths.get("D:\\develop\\Java\\javacode\\wind-chaser\\java-practise\\src\\main\\java\\com\\aseubel\\file\\demo.txt");
    
    public static void main(String[] args) throws IOException {
        read1();
    }

    /**
     * 小文件一次性读完 (Java 11+)
     */
    public static void read1() throws IOException {
        String content = Files.readString(path); // Java 11+
        System.out.println(content);
    }

    /**
     * 小文件一次性读完 (Java 8)
     */
    public static void read2() throws IOException {
        String content = new String(Files.readAllBytes(path));
        System.out.println(content);
    }

    /**
     * 大文件分批读 (Java 11+)
     */
    public static void read3() throws IOException {
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int bytesRead;
        try (var inputStream = Files.newInputStream(path)) {
            while ((bytesRead = inputStream.read(buffer))!= -1) {
                System.out.println(new String(buffer, 0, bytesRead));
            }
        }
    }

    /**
     * 按行读
     */
    public static void read4() throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        }
    }

    /**
     * 经典buffered reader读，兼容旧代码
     */
    public static void read5() throws IOException {
        try (var reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
            }
        }
    }

    /**
     * 大文件→内存映射（GB 级日志，几乎零 copy）
     */
    public static void read6() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("demo.txt", "r");
             FileChannel channel = file.getChannel()) {

            long size = channel.size();
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

            byte[] dst = new byte[(int) size];
            buf.get(dst);
            System.out.println(new String(dst));
        }
    }
}
