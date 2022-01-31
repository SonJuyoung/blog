package com.jy.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 html태그가 섞여서 디자인 됨

    @ColumnDefault("0")
    private int count;

    @ManyToOne //Many=Board, User = One
    @JoinColumn(name = "userId")
    private User user; //db는 오브젝트를 저장할 수 없다. 외래키 사용, 자바는 오브젝트 저장할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;
}
