package cc.lq.blog.system.service.impl;

import cc.lq.blog.system.entity.*;
import cc.lq.blog.system.exception.ResourceNotFoundException;
import cc.lq.blog.system.mapper.ArticleMapper;
import cc.lq.blog.system.mapper.ArticleTagMapper;
import cc.lq.blog.system.mapper.CategoryMapper;
import cc.lq.blog.system.mapper.TagMapper;
import cc.lq.blog.system.service.ArticleService;
import cc.lq.blog.system.util.BlogSystemConstants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 博客文章 服务实现类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {
    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;
    private final ArticleTagMapper articleTagMapper;
    private final TagMapper tagMapper;


    public ArticleServiceImpl(ArticleMapper articleMapper, CategoryMapper categoryMapper, ArticleTagMapper articleTagMapper, TagMapper tagMapper) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.articleTagMapper = articleTagMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public ArticleVO getArticleVOByArticleId(Long articleId) {
        ArticleDO articleDO = this.articleMapper.selectById(articleId);
        if (articleDO == null) {
            throw new ResourceNotFoundException("未找到id为" + articleId + "的文章");
        }
        CategoryDO categoryDO = this.categoryMapper.selectById(articleDO.getCategoryId());
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(categoryDO, categoryVO);
        List<ArticleTagDO> articleTagDOList = this.articleTagMapper.selectList(
                new QueryWrapper<ArticleTagDO>().eq("article_id", articleId));
        List<TagVO> tagList = new ArrayList<>();
        articleTagDOList.forEach(articleTagDO -> {
                    TagDO tagDO = this.tagMapper.selectById(articleTagDO.getTagId());
                    TagVO tagVO = new TagVO(tagDO.getId(), tagDO.getTagName());
                    tagList.add(tagVO);
                }
        );

        ArticleVO articleVO = new ArticleVO();
        articleVO.setCategory(categoryVO);
        articleVO.setTags(tagList);
        BeanUtils.copyProperties(articleDO, articleVO);

        return articleVO;
    }

    @Override
    public Boolean removeArticleByArticleId(Long articleId) {
        ArticleDO articleDO = this.articleMapper.selectById(articleId);
        if(articleDO == null) {
            throw new ResourceNotFoundException("未找到id为" + articleId + "的文章");
        }
        int result = this.articleMapper.deleteById(articleId);
        QueryWrapper<ArticleTagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        this.articleTagMapper.delete(wrapper);
        return result > 0;
    }

    @Override
    public ArticleVO addArticleVO(ArticleVO articleVO) {
        ArticleDO articleDO = new ArticleDO();
        BeanUtils.copyProperties(articleVO, articleDO);
        articleDO.setCategoryId(articleVO.getCategory().getId());

        this.articleMapper.insert(articleDO);

        if (articleVO.getTags() != null && articleVO.getTags().size() > 0) {
            articleVO.getTags().forEach(tagDO -> {
                ArticleTagDO articleTagDO = new ArticleTagDO();
                articleTagDO.setArticleId(articleDO.getId());
                articleTagDO.setTagId(tagDO.getId());
                this.articleTagMapper.insert(articleTagDO);
            });
        }
        articleVO.setId(articleDO.getId());

        return getArticleVOByArticleId(articleVO.getId());
    }

    @Override
    public ArticleVO updateArticleVO(ArticleVO articleVO) {
        if (articleVO.getId() == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        if (articleVO.getCategory() == null || articleVO.getCategory().getId() == null) {
            throw new IllegalArgumentException("类别或类别id不能为空");
        }
        Long categoryId = articleVO.getCategory().getId();
        if (this.categoryMapper.selectById(categoryId) == null) {
            throw new ResourceNotFoundException("找不到id为" + categoryId + "的类别");
        }

        ArticleVO originalArticleVO = getArticleVOByArticleId(articleVO.getId());
        if (originalArticleVO == null) {
            throw new ResourceNotFoundException("找不到id为" + categoryId + "的文章");
        }

        ArticleDO articleDO = new ArticleDO();
        BeanUtils.copyProperties(articleVO, articleDO);
        articleDO.setCategoryId(articleVO.getCategory().getId());
        this.articleMapper.updateById(articleDO);
        List<TagVO> tags = articleVO.getTags();

        QueryWrapper<ArticleTagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleVO.getId());
        this.articleTagMapper.delete(wrapper);
        if (tags != null && tags.size() > 0) {
            tags.forEach(tagVO -> {
                ArticleTagDO articleTagDO = new ArticleTagDO();
                articleTagDO.setArticleId(articleVO.getId());
                articleTagDO.setTagId(tagVO.getId());
                this.articleTagMapper.insert(articleTagDO);
            });
        }
        return getArticleVOByArticleId(articleVO.getId());
    }

    @Override
    public Page<ArticleDO> getArticlePage(Long pageNum, Long pageSize) {
        Page<ArticleDO> page = new Page<>(pageNum, pageSize);
        page.setMaxLimit(BlogSystemConstants.PAGE_MAX_SIZE);
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time").orderByDesc("create_time");
        return this.articleMapper.selectPage(page, wrapper);
    }

    @Override
    public Page<ArticleDO> getArticlePage(Long categoryId, Long pageNum, Long pageSize) {
        Page<ArticleDO> page = new Page<>(pageNum, pageSize);
        page.setMaxLimit(BlogSystemConstants.PAGE_MAX_SIZE);
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId)
                .orderByDesc("update_time").orderByDesc("create_time");
        return this.articleMapper.selectPage(page, wrapper);
    }

}
