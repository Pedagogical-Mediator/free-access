package com.example.freeaccess.service.news;

import com.example.freeaccess.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteNews {

    private final NewsRepository newsRepository;

    public DeleteNews(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void execute(Integer id) {
        this.newsRepository.deleteById(id);
    }

}
