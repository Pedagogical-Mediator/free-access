package com.example.freeaccess.service.notice;

import com.example.freeaccess.domain.notice.Notice;
import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaveNotice {

    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;
    private List<NoticeAction> actions;

    public SaveNotice(NoticeRepository noticeRepository, ModelMapper modelMapper, List<NoticeAction> actions) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
        this.actions = actions;
    }


    public NoticeDTO execute(NoticeDTO noticeDTO) {
        Notice notice = this.modelMapper.map(noticeDTO, Notice.class);

        notice.setCreationDate(LocalDate.now());
        notice = this.noticeRepository.save(notice);

        NoticeDTO returnedNoticeDTO = this.modelMapper.map(notice, NoticeDTO.class);
        this.actions.forEach(action -> action.execute(returnedNoticeDTO));

        return returnedNoticeDTO;
    }

}