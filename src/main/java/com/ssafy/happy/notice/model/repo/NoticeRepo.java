package com.ssafy.happy.notice.model.repo;

import java.util.List;

import com.ssafy.happy.notice.dto.Notice;
import com.ssafy.happy.notice.dto.NoticeSearchCondition;

public interface NoticeRepo {
    boolean insert(Notice boardNo);
    boolean update(Notice boardNo);
    boolean delete(int articleNo);
    Notice select(int articleNo);
    List<Notice> search(NoticeSearchCondition condition);
    void increaseHit(int articleNo);
    int getTotalSearchCount(NoticeSearchCondition condition);
}
