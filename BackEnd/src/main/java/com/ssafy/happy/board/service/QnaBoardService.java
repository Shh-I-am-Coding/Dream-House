package com.ssafy.happy.board.service;

import com.ssafy.happy.board.domain.QnaBoard;
import com.ssafy.happy.board.dto.QnaBoardModifyRequest;
import com.ssafy.happy.board.dto.QnaBoardRequest;
import com.ssafy.happy.board.dto.QnaBoardResponse;
import com.ssafy.happy.board.dto.QnaBoardSearchRequest;
import com.ssafy.happy.board.repository.QnaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaBoardService {
    private final QnaBoardRepository qnaBoardRepository;

    public void create(QnaBoardRequest qnaBoardRequest) {
        qnaBoardRepository.save(qnaBoardRequest.toEntity());
    }

    public void update(Long id, QnaBoardModifyRequest qnaBoardModifyRequest) {
        QnaBoard qnaBoard = qnaBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        qnaBoard.update(qnaBoardModifyRequest);
    }

    public void delete(Long id) {
        qnaBoardRepository.delete(qnaBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다.")));
    }

    @Transactional(readOnly = true)
    public QnaBoardResponse findOne(Long id) {
        return QnaBoardResponse.of(qnaBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다.")));
    }

    @Transactional(readOnly = true)
    public Page<QnaBoardResponse> findAll(QnaBoardSearchRequest qnaBoardSearchRequest) {
        String key = qnaBoardSearchRequest.getKey();
        Page<QnaBoard> qnaBoardPage;
        PageRequest pageRequest = PageRequest.of(qnaBoardSearchRequest.getPageNum(), 10, Direction.DESC, "createdDate");

        switch (key) {
            case "none" :
                qnaBoardPage = qnaBoardRepository.findAll(pageRequest);
                break;
            case "title":
                qnaBoardPage = qnaBoardRepository.findQnaBoardsByTitleContaining(
                        qnaBoardSearchRequest.getWord(), pageRequest);
                break;
            case "userId":
                qnaBoardPage = qnaBoardRepository.findQnaBoardsByUserId(
                        Long.parseLong(qnaBoardSearchRequest.getWord()), pageRequest);
                break;
            case "content":
                qnaBoardPage = qnaBoardRepository.findQnaBoardsByContentContaining(
                        qnaBoardSearchRequest.getWord(), pageRequest);
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 검색 키워드입니다.");
        }
        return qnaBoardPage.map(QnaBoardResponse::of);
    }
}
