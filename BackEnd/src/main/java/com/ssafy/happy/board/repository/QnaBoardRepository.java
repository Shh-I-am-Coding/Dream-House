package com.ssafy.happy.board.repository;

import com.ssafy.happy.board.domain.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {
    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select qnaBoard from QnaBoard qnaBoard left join qnaBoard.user")
    Page<QnaBoard> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select qnaBoard from QnaBoard qnaBoard left join qnaBoard.user where qnaBoard.content like concat('%',:content,'%')")
    Page<QnaBoard> findQnaBoardsByContentContaining(String content, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select qnaBoard from QnaBoard qnaBoard left join qnaBoard.user where qnaBoard.user.id = :userId")
    Page<QnaBoard> findQnaBoardsByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select qnaBoard from QnaBoard qnaBoard left join qnaBoard.user where qnaBoard.title like concat('%',:title,'%')")
    Page<QnaBoard> findQnaBoardsByTitleContaining(String title, Pageable pageable);
}
