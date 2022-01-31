package com.jy.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert //insert시 null인 필드를 생략하고 insert
public class User {

    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user")
    //DB는 RoleType이라는 것 없다, 그래서 아래 어노테이션
    @Enumerated(EnumType.STRING)//해당 enum이 스트링이라고 알려줌
    private RoleType role; //Enum을 쓰는게 좋다(오타 혹은 다른 값 못넣고 Enum 안에 있는 값만 가능)

    @CreationTimestamp
    private Timestamp createDate;
}
