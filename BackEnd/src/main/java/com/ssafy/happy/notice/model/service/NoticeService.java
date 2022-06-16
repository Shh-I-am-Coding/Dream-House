package com.ssafy.happy.notice.model.service;

import java.util.Map;

import com.ssafy.happy.notice.dto.Notice;
import com.ssafy.happy.notice.dto.NoticeSearchCondition;

public interface NoticeService {
    boolean insert(Notice notice);
    boolean update(Notice notice);
    boolean delete(int articleNo);
    Notice select(int articleNo);
    Map<String, Object> search(NoticeSearchCondition condition);
    void increaseHit(int articleNo);
}
