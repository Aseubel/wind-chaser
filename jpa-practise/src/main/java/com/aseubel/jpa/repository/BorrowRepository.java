package com.aseubel.jpa.repository;

import com.aseubel.jpa.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/14 下午12:43
 */
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    // 查询某个用户的借阅记录
    @Query("SELECT b FROM Borrow b WHERE b.user.uid = :userId")
    List<Borrow> findBorrowsByUserId(@Param("userId") Integer userId);

    // 查询某个书的借阅记录
    @Query("SELECT b FROM Borrow b WHERE b.book.bid = :bookId")
    List<Borrow> findBorrowsByBookId(@Param("bookId") Integer bookId);
}
