package com.example.freeaccess.service.notice;

import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindNoticeById {

    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    public FindNoticeById(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }

    public NoticeDTO execute(Integer id) {
        return this.modelMapper.map(this.noticeRepository.findById(id), NoticeDTO.class);
    }
}