package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.Application;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper mapper;

    private Article article;

    @Before
    public void setUp() {
        ArticleRepository repository = new ArticleRepository();
        List<Article> list = repository.all();
        article = list.get(0);
    }

    @Test
    public void map_can_convert_an_Article_to_an_ArticleDto() {
        ArticleDto dto = mapper.map(article);

        assertEquals("ID have to match", article.getId(), dto.getId());
        assertEquals("description have to match", article.getDescription(), dto.getDescription());
        assertEquals("Author have to match", article.getAuthor(), dto.getAuthor());
        assertEquals("Title have to match", article.getTitle(), dto.getTitle());
        assertEquals("Number of block elements have to match", article.getBlocks().size(), dto.getBlocks().size());
    }

    @Test
    public void it_returns_the_article_blocks_in_the_right_order() {
        ArticleDto dto = mapper.map(article);
        Collection<ArticleBlockDto> blocks = dto.getBlocks();

        int prevSortIndex = -1;

        for (ArticleBlockDto block : blocks) {

            if (prevSortIndex == -1) {
                prevSortIndex = block.getSortIndex();
            } else {
                int curr = block.getSortIndex();
                assertTrue("Previous SortIndex (" + prevSortIndex + ") should be lower than current (" + curr + ")", prevSortIndex <= curr);
            }
        }
    }

}
