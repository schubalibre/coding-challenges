package com.mhp.coding.challenges.mapping.controllers;

import com.mhp.coding.challenges.mapping.exceptions.ArticleNotFoundException;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(produces = "application/json")
    public List<ArticleDto> list() {
        return articleService.list();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ArticleDto> details(@PathVariable Long id) {
        try {
            ArticleDto article = articleService.articleForId(id);
            return ResponseEntity.ok(article);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ArticleDto create(@RequestBody ArticleDto articleDto) {
        return articleService.create(articleDto);
    }
}
