package com.aseubel.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Aseubel
 * @date 2025/6/14 下午12:12
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrow")
public class Borrow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "bid", insertable = false, updatable = false)
    private Book book;

    @Column(name = "uid")
    private Long uid;
    @Column(name = "bid")
    private Long bid;

    // 重写toString方法避免调用user或book的toString方法导致死循环
    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", user=" + user.getUid() + "-" + user.getName() +
                ", book=" + book.getBid() + "-" + book.getTitle() +
                '}';
    }

    public Borrow(User user, Book book) {
        this.user = user;
        this.book = book;
        this.uid = user.getUid();
        this.bid = book.getBid();
    }

    public String bookInfo() {
        return book.getTitle() + " - " + book.getDescription();
    }

    public String userInfo() {
        return user.getName() + " - " + user.getAge() + " - " + user.getSex();
    }
}
