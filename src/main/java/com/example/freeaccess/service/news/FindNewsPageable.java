package com.example.freeaccess.service.news;

import com.example.freeaccess.domain.news.NewsDTO;
import com.example.freeaccess.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindNewsPageable {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    public FindNewsPageable(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    public Page<NewsDTO> execute(Pageable pageable) {
        return this.newsRepository.findAll(pageable).map(news -> this.modelMapper.map(news, NewsDTO.class));
    }

}
