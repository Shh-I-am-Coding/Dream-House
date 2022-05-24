package com.ssafy.happy.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class NoticeSearchCondition {
    // 한 페이지에 몇개나 보여줄 것인지 결정한다.
    public final int countPerPage = 10;

    // 검색 컬럼으로 기본인 none은 검색하지 않는다. ex) isbn
    private String key = "none";

    // 검색어: ex: 111-222-3333
    private String word;

    // 현재 페이지 번호로 offset을 계산할 때 사용된다. 기본 페이지는 1이다.
    private int currentPage = 1;

    private boolean limit = true;

    public NoticeSearchCondition(String key, String word, int currentPage) {
        this.key = key;
        this.word = word;
        this.currentPage = currentPage;
    }

    public int getOffset() {
        return (this.currentPage - 1) * countPerPage;
    }
}