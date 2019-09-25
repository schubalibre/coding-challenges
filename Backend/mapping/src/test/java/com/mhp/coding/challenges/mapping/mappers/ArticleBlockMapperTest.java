package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.exceptions.UnknownArticleBlockTypeException;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.ImageSize;
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.UnknownBlockType;
import com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlockType;
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArticleBlockMapperTest {
    private ArticleBlockMapper mapper;

    @Before
    public void setUp() {
        mapper = new ArticleBlockMapper();
    }

    @Test
    public void it_can_map_a_ImageBlock_to_it_dto() {

        final Image image = createImage();

        final com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock imgBlock = new com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock();
        imgBlock.setSortIndex(202);
        imgBlock.setImage(image);

        final ImageBlock dto = mapper.mapToDto(imgBlock);

        assertEquals("ID have to match", imgBlock.getImage().getId(), dto.getImage().getId());
        assertEquals("Url have to match", imgBlock.getImage().getUrl(), dto.getImage().getUrl());
        assertEquals("ImageSize have to match", imgBlock.getImage().getImageSize(), dto.getImage().getImageSize());
        assertEquals("SortIndex have to match", imgBlock.getSortIndex(), dto.getSortIndex());
    }


    @Test
    public void it_can_map_a_GalleryBlock_to_it_dto() {

        final Image image = createImage();

        final List<Image> list = new LinkedList<>();
        list.add(image);

        final GalleryBlock galleryBlock = new GalleryBlock();
        galleryBlock.setSortIndex(202);
        galleryBlock.setImages(list);

        final GalleryBlockDto dto = mapper.mapToDto(galleryBlock);
        assertEquals("GalleryBlock -> Image -> Url", image.getUrl(), dto.getImages().get(0).getUrl());
        assertEquals("SortIndex have to match", galleryBlock.getSortIndex(), dto.getSortIndex());
    }

    @Test
    public void it_can_map_a_TextBlock_to_it_dto() {
        final com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock textBlock = new com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock();

        textBlock.setText("text test");
        textBlock.setSortIndex(202);

        final TextBlock dto = mapper.mapToDto(textBlock);
        assertEquals("Text have to match", textBlock.getText(), dto.getText());
        assertEquals("SortIndex have to match", textBlock.getSortIndex(), dto.getSortIndex());
    }

    @Test
    public void it_can_map_a_VideoBlock_to_it_dto() {

        final com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock videoBlock = new com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock();

        videoBlock.setUrl("http://test/");
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setSortIndex(202);

        final VideoBlock dto = mapper.mapToDto(videoBlock);
        assertEquals("Text have to match", videoBlock.getUrl(), dto.getUrl());
        assertEquals("Text have to match", videoBlock.getType(), dto.getType());
        assertEquals("SortIndex have to match", videoBlock.getSortIndex(), dto.getSortIndex());
    }

    @Test(expected = UnknownArticleBlockTypeException.class)
    public void it_throws_an_exception_if_there_is_no_mapper() {
        final UnknownBlockType block = new UnknownBlockType();
        mapper.mapToDto(block);
    }

    private Image createImage() {
        final Image image = new Image();
        image.setId(101L);
        image.setUrl("http://test/");
        image.setImageSize(ImageSize.MEDIUM);
        return image;
    }
}