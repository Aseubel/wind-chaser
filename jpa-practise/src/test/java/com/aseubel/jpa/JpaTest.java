package com.aseubel.jpa;

import com.aseubel.jpa.entity.User;
import com.aseubel.jpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/13 下午8:35
 */
@DataJpaTest // 轻量级的测试环境
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 禁用自动配置的数据库
//@SpringBootTest
public class JpaTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void jpaTest() {
        User user = new User();
        user.setName("小白");
        user.setAge(18);
        user.setSex(User.Sex.男);
        User savedUser = userRepository.save(user);
        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findById(user.getUid()).orElseThrow());
        userRepository.delete(savedUser);
    }

    @Test
    void jpaSortTest() {
        Sort.TypedSort<User> sortType = Sort.sort(User.class);
        Sort sort = sortType.by(User::getAge).descending().and(sortType.by(User::getUid));
        Page<User> all = userRepository.findAll(PageRequest.of(0, 5 ,sort));

        System.out.println("total pages: " + all.getTotalPages());
        System.out.println("total elements: " + all.getSize());
        System.out.println("current page: " + all.getNumber());
        System.out.println("total elements: " + all.getTotalElements());
        System.out.println("content: " + all.getContent());
    }

    @Test
    void qbeAndSortTest() {
        Sort.TypedSort<User> sortType = Sort.sort(User.class);
        Sort sort = sortType.by(User::getAge).descending().and(sortType.by(User::getUid));
        Example<User> example = Example.of(User.builder().sex(User.Sex.男).build());

        Page<User> all = userRepository.findAll(example, PageRequest.of(0, 2 ,sort));
        System.out.println(all.getContent());
    }

    @Test
    void betweenTest() {
        List<User> users = userRepository.findByAgeBetween(16, 19);
        System.out.println(users);
    }

    @Test
    @Transactional
    @Commit
    void versionTest() {
        String name = "小天";
        User user = new User();
        user.setName(name);
        user.setAge(11);
        user.setSex(User.Sex.男);
        userRepository.save(user);

        User user1 = userRepository.findUserByName(name);
        System.out.println(user1);
        Assertions.assertEquals(11, user1.getAge());
        Assertions.assertEquals(0L, user1.getVersion());
        user1.setAge(12);
        userRepository.save(user1);

        User user2 = userRepository.findUserByName(name);
        Assertions.assertEquals(12, user2.getAge());
        Assertions.assertEquals(1L, user2.getVersion());
    }

    @Test
    void auditorTest() {

    }
}
