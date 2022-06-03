package com.example.freeaccess.service.notice;

import com.example.freeaccess.domain.notice.NoticeDTO;
import org.springframework.stereotype.Service;

@Service
public interface NoticeAction {
    void execute(NoticeDTO noticeDTO);
}
