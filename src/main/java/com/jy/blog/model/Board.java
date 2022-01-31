package com.jy.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity //ORM : JAVA OBJECT -> 테이블로 매핑
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관광계의 주인이 아니다(외래키 아님), DB에 컬럼 생성X
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
