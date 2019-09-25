package com.mhp.coding.challenges.mapping.models.db.blocks;

import com.mhp.coding.challenges.mapping.mappers.ArticleBlockMapper;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

import java.util.List;

public class GalleryBlock extends ArticleBlock {

    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public ArticleBlockDto map(ArticleBlockMapper mapper) {
        return mapper.mapToDto(this);
    }
}
