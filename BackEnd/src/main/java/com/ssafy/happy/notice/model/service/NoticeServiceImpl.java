package com.ssafy.happy.notice.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happy.notice.dto.Notice;
import com.ssafy.happy.notice.model.repo.NoticeRepo;
import com.ssafy.happy.util.PageNavigation;
import com.ssafy.happy.notice.dto.NoticeSearchCondition;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepo noticeRepo;

    public NoticeServiceImpl(NoticeRepo noticeRepo) {
        this.noticeRepo = noticeRepo;
    }

    @Override
    public boolean insert(Notice notice) {
        return noticeRepo.insert(notice);
    }

    @Override
    public boolean update(Notice notice) {
        return noticeRepo.update(notice);
    }

    @Override
    public boolean delete(int articleNo) {
        return noticeRepo.delete(articleNo);
    }

    @Override
    public Notice select(int articleNo) {
        return noticeRepo.select(articleNo);
    }

    @Override
    public Map<String, Object> search(NoticeSearchCondition noticeSearchCondition) {
        int totalCount = noticeRepo.getTotalSearchCount(noticeSearchCondition);
        PageNavigation pageNavigation = new PageNavigation(noticeSearchCondition.getCurrentPage() ,totalCount);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", noticeRepo.search(noticeSearchCondition));
        map.put("pageNavigation", pageNavigation);
        return map;
    }

    @Override
    public void increaseHit(int articleNo) {
        noticeRepo.increaseHit(articleNo);
    }
}
