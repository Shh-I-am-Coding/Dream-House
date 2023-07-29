package com.ssafy.happy.board.repository;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.board.constant.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.category = :category")
    Page<Board> findAllByCategory(Category category, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.category = :category and board.content like concat('%',:content,'%')")
    Page<Board> findBoardsByCategoryAndContentContaining(Category category, String content, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.category = :category and board.user.id = :userId")
    Page<Board> findBoardsByCategoryAndUserId(Category category, Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select board from Board board left join board.user where board.category = :category and board.title like concat('%',:title,'%')")
    Page<Board> findBoardsByCategoryAndTitleContaining(Category category, String title, Pageable pageable);
}
