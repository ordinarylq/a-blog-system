package cc.lq.blog.system.service.impl;

import cc.lq.blog.system.entity.ArticleDO;
import cc.lq.blog.system.mapper.ArticleMapper;
import cc.lq.blog.system.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章 服务实现类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {

}
