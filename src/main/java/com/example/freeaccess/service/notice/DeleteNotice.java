package com.example.freeaccess.service.notice;

import com.example.freeaccess.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteNotice {

    private final NoticeRepository noticeRepository;

    public DeleteNotice(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public void execute(Integer id){
        this.noticeRepository.deleteById(id);
    }

}
