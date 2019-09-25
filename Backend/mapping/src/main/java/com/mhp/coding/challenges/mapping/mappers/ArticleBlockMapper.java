package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.exceptions.UnknownArticleBlockTypeException;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.*;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class ArticleBlockMapper {

    private ImageDto getImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setImageSize(image.getImageSize());
        imageDto.setUrl(image.getUrl());
        return imageDto;
    }

    public ImageBlock mapToDto(com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock imageBlock) {
        ImageBlock block = new ImageBlock();
        ImageDto dto = getImageDto(imageBlock.getImage());
        block.setImage(dto);
        block.setSortIndex(imageBlock.getSortIndex());

        return block;
    }

    public GalleryBlockDto mapToDto(GalleryBlock galleryBlock) {
        GalleryBlockDto dto = new GalleryBlockDto();
        dto.setImages(galleryBlock
                .getImages()
                .stream()
                .map(this::getImageDto)
                .collect(toList())
        );
        dto.setSortIndex(galleryBlock.getSortIndex());
        return dto;
    }

    public TextBlock mapToDto(com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock textBlock) {
        TextBlock block = new TextBlock();
        block.setSortIndex(textBlock.getSortIndex());
        block.setText(textBlock.getText());
        return block;
    }

    public VideoBlock mapToDto(com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock videoBlock) {
        VideoBlock block = new VideoBlock();
        block.setType(videoBlock.getType());
        block.setUrl(videoBlock.getUrl());
        block.setSortIndex(videoBlock.getSortIndex());
        return block;
    }

    public ArticleBlockDto mapToDto(ArticleBlock articleBlock) {
        throw new UnknownArticleBlockTypeException(articleBlock.getClass().getSimpleName());
    }
}
