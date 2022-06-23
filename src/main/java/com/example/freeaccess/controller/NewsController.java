package com.example.freeaccess.controller;

import com.example.freeaccess.domain.news.NewsDTO;
import com.example.freeaccess.service.news.DeleteNews;
import com.example.freeaccess.service.news.FindNewsPageable;
import com.example.freeaccess.service.news.SaveNews;
import com.example.freeaccess.service.news.UpdateNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/school/news")
public class NewsController {

    private final SaveNews save;
    private final FindNewsPageable findNewsPageable;
    private final UpdateNews updateNews;
    private final DeleteNews deleteNews;

    public NewsController(SaveNews save, FindNewsPageable findNewsPageable, UpdateNews updateNews, DeleteNews deleteNews) {
        this.save = save;
        this.findNewsPageable = findNewsPageable;
        this.updateNews = updateNews;
        this.deleteNews = deleteNews;
    }

    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO newsDTO) {
        newsDTO = this.save.execute(newsDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newsDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newsDTO);
    }

    @GetMapping
    public ResponseEntity<Page<NewsDTO>> findPage(@PageableDefault(sort = "creationDate", page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(this.findNewsPageable.execute(pageable));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Integer id, @RequestBody NewsDTO newsDTO) {
        newsDTO.setId(id);

        NewsDTO returnedNews = this.updateNews.execute(newsDTO);

        return ResponseEntity.ok().body(returnedNews);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.deleteNews.execute(id);

        return ResponseEntity.ok().build();
    }
}
