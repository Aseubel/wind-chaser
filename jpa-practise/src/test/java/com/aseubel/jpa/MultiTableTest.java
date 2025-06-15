package com.aseubel.jpa;

import com.aseubel.jpa.entity.Book;
import com.aseubel.jpa.entity.User;
import com.aseubel.jpa.repository.BookRepository;
import com.aseubel.jpa.repository.BorrowRepository;
import com.aseubel.jpa.repository.UserRepository;
import com.aseubel.jpa.entity.Borrow;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/14 下午12:46
 */
@DataJpaTest // 轻量级的测试环境
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 禁用自动配置的数据库
@Slf4j
//@SpringBootTest
public class MultiTableTest {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void borrowQueryTest() {
        List<Borrow> borrowsByBookId = borrowRepository.findBorrowsByBookId(1);
        System.out.println(borrowsByBookId);
        System.out.println("----------------------");
        List<Borrow> borrowsByUserId = borrowRepository.findBorrowsByUserId(1);
        System.out.println(borrowsByUserId);
    }

    @Test
    public void userQueryTest() {
        userRepository.findById(1L).ifPresent(System.out::println);
    }

    @Test
    public void bookQueryTest() {
        bookRepository.findById(4L).ifPresent(System.out::println);
    }

    @Test
    public void borrowInsertTest() {
        Long uid = 3L;
        Long bid = 4L;
        User user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("User not found with id: " + uid));
        Book book = bookRepository.findById(bid).orElseThrow(() -> new RuntimeException("Book not found with id: " + bid));

        Borrow borrow = new Borrow(user, book);
        borrowRepository.save(borrow);
        System.out.println(borrow);

        System.out.println("--------------");
        List<Borrow> borrowsByBookId = borrowRepository.findBorrowsByBookId(4);
        System.out.println(borrowsByBookId);
        System.out.println("-");
        List<Borrow> borrowsByUserId = borrowRepository.findBorrowsByUserId(3);
        System.out.println(borrowsByUserId);
        System.out.println("-");
        List<Borrow> borrows = borrowRepository.findAll();
        System.out.println(borrows);
    }
}
