package com.example.freeaccess.service.news;

import com.example.freeaccess.domain.news.News;
import com.example.freeaccess.domain.news.NewsDTO;
import com.example.freeaccess.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateNews {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    public UpdateNews(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    public NewsDTO execute(NewsDTO newsDTO) {
        News news = this.newsRepository.findById(newsDTO.getId()).orElseThrow();
        this.modelMapper.map(newsDTO, news);

        News returnedNews = this.newsRepository.save(news);

        return this.modelMapper.map(returnedNews, NewsDTO.class);
    }

}
