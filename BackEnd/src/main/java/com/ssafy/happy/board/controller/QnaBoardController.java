package com.ssafy.happy.board.controller;

import com.ssafy.happy.board.dto.QnaBoardModifyRequest;
import com.ssafy.happy.board.dto.QnaBoardRequest;
import com.ssafy.happy.board.dto.QnaBoardResponse;
import com.ssafy.happy.board.dto.QnaBoardSearchRequest;
import com.ssafy.happy.board.service.QnaBoardService;
import com.ssafy.happy.common.dto.ApiResponse;
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
public class QnaBoardController {
    private final QnaBoardService qnaBoardService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody QnaBoardRequest qnaBoardRequest) {
        qnaBoardService.create(qnaBoardRequest);
        return ApiResponse.successWithNoContent();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id,
                                                 @RequestBody QnaBoardModifyRequest qnaBoardModifyRequest) {
        qnaBoardService.update(id, qnaBoardModifyRequest);
        return ApiResponse.successWithNoContent();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        qnaBoardService.delete(id);
        return ApiResponse.successWithNoContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QnaBoardResponse>> findBoard(@PathVariable Long id) {
        return ApiResponse.successWithData(qnaBoardService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<QnaBoardResponse>>> search(
            @ModelAttribute QnaBoardSearchRequest qnaBoardSearchRequest) {
        return ApiResponse.successWithData(qnaBoardService.findAll(qnaBoardSearchRequest));
    }

}
