package com.jy.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) //Many=Board, User = One
    @JoinColumn(name = "userId")
    private User user; //db는 오브젝트를 저장할 수 없다. 외래키 사용, 자바는 오브젝트 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다(외래키 아님), DB에 컬럼 생성X, 보드 셀렉트 할 때 들고 옴
    @JsonIgnoreProperties({"board"}) //무한 참조 방지, board를 통해서 replies 참조 시 무시, 다이렉트로 접근 시 참조 됨
    @OrderBy("createDate desc")
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}
