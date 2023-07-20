package com.ssafy.happy.board.domain;

import com.ssafy.happy.board.dto.QnaBoardModifyRequest;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int hit;

    private LocalDateTime createdDate;

    public void update(QnaBoardModifyRequest qnaBoardModifyRequest) {
        title = qnaBoardModifyRequest.getTitle();
        content = qnaBoardModifyRequest.getContent();
        hit = qnaBoardModifyRequest.getHit();
    }
}
