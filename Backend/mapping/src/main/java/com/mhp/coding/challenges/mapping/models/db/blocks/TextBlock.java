package com.mhp.coding.challenges.mapping.models.db.blocks;

import com.mhp.coding.challenges.mapping.mappers.ArticleBlockMapper;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

public class TextBlock extends ArticleBlock {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public ArticleBlockDto map(ArticleBlockMapper mapper) {
        return mapper.mapToDto(this);
    }
}
