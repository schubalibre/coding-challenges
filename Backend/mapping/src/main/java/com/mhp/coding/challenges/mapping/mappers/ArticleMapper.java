package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;


@Component
public class ArticleMapper {

    private final ArticleBlockMapper mapper;

    @Autowired
    public ArticleMapper() {
        this.mapper = new ArticleBlockMapper();
    }

    public ArticleDto map(Article article) {
        return mapToDto(article);
    }

    private ArticleDto mapToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setDescription(article.getDescription());
        articleDto.setAuthor(article.getAuthor());
        Set<ArticleBlockDto> blocks = new LinkedHashSet<>();
        articleDto.setBlocks(blocks);

        article.getBlocks().stream()
                .sorted(Comparator.comparingInt(ArticleBlock::getSortIndex))
                .map(this::mapBlock)
                .forEach(blocks::add);

        return articleDto;
    }

    private ArticleBlockDto mapBlock(ArticleBlock articleBlock) {
        return articleBlock.map(mapper);
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }
}
