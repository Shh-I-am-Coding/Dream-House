package com.ssafy.happy.board.controller;

import com.ssafy.happy.board.dto.BoardModifyRequest;
import com.ssafy.happy.board.dto.BoardRequest;
import com.ssafy.happy.board.dto.BoardResponse;
import com.ssafy.happy.board.dto.BoardSearchRequest;
import com.ssafy.happy.board.service.BoardService;
import com.ssafy.happy.common.dto.ApiResponse;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @ApiOperation(value = "게시판 글 생성")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody @Valid BoardRequest boardRequest) {
        boardService.create(boardRequest);
        return ApiResponse.successWithNoContent();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "게시판 글 수정")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id,
                                                 @RequestBody @Valid BoardModifyRequest boardModifyRequest) {
        boardService.update(id, boardModifyRequest);
        return ApiResponse.successWithNoContent();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "게시판 글 삭제")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ApiResponse.successWithNoContent();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "게시판 글 상세 조회")
    public ResponseEntity<ApiResponse<BoardResponse>> findBoard(@PathVariable Long id) {
        return ApiResponse.successWithData(boardService.findOne(id));
    }

    @GetMapping
    @ApiOperation(value = "게시판 글 목록 조회 - 페이징", notes = "글 검색도 제공")
    public ResponseEntity<ApiResponse<Page<BoardResponse>>> search(
            @ModelAttribute @Valid BoardSearchRequest boardSearchRequest) {
        return ApiResponse.successWithData(boardService.findAll(boardSearchRequest));
    }

}
