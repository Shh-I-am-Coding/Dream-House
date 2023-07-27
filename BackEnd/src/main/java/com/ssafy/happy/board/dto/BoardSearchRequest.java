package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardSearchRequest {
    @NotBlank
    private Category category;

    private String key = "none";

    private String word;

    @PositiveOrZero
    private int pageNum = 0;
}
