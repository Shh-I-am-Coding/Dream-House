package com.ssafy.happy.board.controller;

import com.ssafy.happy.board.dto.BoardModifyRequest;
import com.ssafy.happy.board.dto.BoardRequest;
import com.ssafy.happy.board.dto.BoardResponse;
import com.ssafy.happy.board.dto.BoardSearchRequest;
import com.ssafy.happy.board.service.BoardService;
import com.ssafy.happy.common.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<?>> create(@RequestBody @Valid BoardRequest boardRequest) {
        boardService.create(boardRequest);
        return ApiResponse.successWithNoContent();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id,
                                                 @RequestBody @Valid BoardModifyRequest boardModifyRequest) {
        boardService.update(id, boardModifyRequest);
        return ApiResponse.successWithNoContent();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ApiResponse.successWithNoContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardResponse>> findBoard(@PathVariable Long id) {
        return ApiResponse.successWithData(boardService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BoardResponse>>> search(
            @ModelAttribute @Valid BoardSearchRequest boardSearchRequest) {
        return ApiResponse.successWithData(boardService.findAll(boardSearchRequest));
    }

}
