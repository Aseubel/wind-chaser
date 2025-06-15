package com.aseubel.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/13 下午8:40
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
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Borrow> borrows;

    @Getter
    public enum Sex {
        男("男"),
        女("女");

        private final String sex;

        Sex(String sex) {
            this.sex = sex;
        }

    }
}
