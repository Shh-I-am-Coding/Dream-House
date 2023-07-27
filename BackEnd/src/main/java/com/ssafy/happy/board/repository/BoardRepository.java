package com.ssafy.happy.board.repository;

import com.ssafy.happy.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user")
    Page<Board> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.content like concat('%',:content,'%')")
    Page<Board> findQnaBoardsByContentContaining(String content, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.user.id = :userId")
    Page<Board> findQnaBoardsByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.title like concat('%',:title,'%')")
    Page<Board> findQnaBoardsByTitleContaining(String title, Pageable pageable);
}
