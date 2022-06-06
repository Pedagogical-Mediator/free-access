package com.example.freeaccess.service.notice;

import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllNotices {

    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    public FindAllNotices(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }

    public Page<NoticeDTO> execute(Pageable pageable) {
        return this.noticeRepository.findAll(pageable).map(notice -> this.modelMapper.map(notice, NoticeDTO.class));
    }
}
