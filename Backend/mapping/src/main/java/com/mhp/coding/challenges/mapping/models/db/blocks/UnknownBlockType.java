package com.mhp.coding.challenges.mapping.models.db.blocks;

import com.mhp.coding.challenges.mapping.mappers.ArticleBlockMapper;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

public class UnknownBlockType extends ArticleBlock {
    @Override
    public ArticleBlockDto map(ArticleBlockMapper mapper) {
        return mapper.mapToDto(this);
    }
}
