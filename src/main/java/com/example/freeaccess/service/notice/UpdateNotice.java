package com.example.freeaccess.service.notice;

import com.example.freeaccess.domain.notice.Notice;
import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateNotice {

    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    public UpdateNotice(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }

    public NoticeDTO execute(NoticeDTO noticeDTO) {
        Notice notice = this.noticeRepository.findById(noticeDTO.getId()).orElseThrow();
        this.modelMapper.map(noticeDTO, notice);
        Notice returned = this.noticeRepository.save(notice);
        return this.modelMapper.map(returned, NoticeDTO.class);
    }
}
