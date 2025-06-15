package com.aseubel.jpa.repository;

import com.aseubel.jpa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aseubel
 * @date 2025/6/15 下午12:10
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
