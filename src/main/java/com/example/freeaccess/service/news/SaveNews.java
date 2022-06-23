package com.example.freeaccess.service.news;

import com.example.freeaccess.domain.news.News;
import com.example.freeaccess.domain.news.NewsDTO;
import com.example.freeaccess.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaveNews {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;
    private final SendPushNotification sendPushNotification;

    public SaveNews(NewsRepository newsRepository, ModelMapper modelMapper, SendPushNotification sendPushNotification) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
        this.sendPushNotification = sendPushNotification;
    }

    public NewsDTO execute(NewsDTO newsDTO) {
        News news = this.modelMapper.map(newsDTO, News.class);

        news.setCreationDate(LocalDate.now());
        news = this.newsRepository.save(news);

        this.sendPushNotification.execute(newsDTO);
        return this.modelMapper.map(news, NewsDTO.class);
    }

}
