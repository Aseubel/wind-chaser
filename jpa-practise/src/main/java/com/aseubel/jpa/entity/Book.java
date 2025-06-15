package com.aseubel.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/14 下午12:34
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "book")
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;
    @Column(name = "title")
    private String title;
    @Column(name = "des")
    private String description;
    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows;
}
