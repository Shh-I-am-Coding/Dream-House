package com.ssafy.happy.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaBoardModifyRequest {
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank
    private String content;

    @NotNull
    @PositiveOrZero
    private int hit;
}
