package com.ssafy.happy.board.repository;

import com.ssafy.happy.board.domain.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {
    Page<QnaBoard> findQnaBoardsByContentContaining(String content, Pageable pageable);
    Page<QnaBoard> findQnaBoardsByUserId(Long userId, Pageable pageable);
    Page<QnaBoard> findQnaBoardsByTitleContaining(String title, Pageable pageable);
}
