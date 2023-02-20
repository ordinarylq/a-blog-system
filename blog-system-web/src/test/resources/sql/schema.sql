DROP TABLE IF EXISTS blog_article;

CREATE TABLE `blog_article`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `title`       varchar(100)    NOT NULL COMMENT '文章标题',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户id',
    `subtitle`    varchar(100)             DEFAULT NULL COMMENT '文章副标题',
    `content`     text            NOT NULL COMMENT '文章内容',
    `category_id` bigint unsigned NOT NULL COMMENT '文章分类id',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)      NOT NULL DEFAULT '0' COMMENT '删除标识 1-是 0-否',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='博客文章';

DROP TABLE IF EXISTS blog_category;
CREATE TABLE `blog_category`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `category_name`  varchar(45)     NOT NULL COMMENT '分类名称',
    `category_order` int             NOT NULL COMMENT '分类排序',
    `create_time`    datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`     tinyint(1)      NOT NULL DEFAULT '0' COMMENT '删除标识 1-是 0-否',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='博客分类';

DROP TABLE IF EXISTS blog_tag;
CREATE TABLE `blog_tag`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
    `tag_name`    varchar(45)     NOT NULL COMMENT '标签名',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)      NOT NULL DEFAULT '0' COMMENT '删除标识 1-是 0-否',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='博客标签';

DROP TABLE IF EXISTS blog_article_tag;
CREATE TABLE `blog_article_tag`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '文章-标签关系id',
    `article_id`  bigint          NOT NULL COMMENT '文章id',
    `tag_id`      bigint          NOT NULL COMMENT '标签id',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_article_tag` (`article_id`, `tag_id`) /*!80000 INVISIBLE */
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='博客文章标签';

DROP TABLE IF EXISTS blog_user;
CREATE TABLE `blog_user`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(255)    NOT NULL COMMENT '用户名',
    `secret_key`  varchar(255)    NOT NULL COMMENT '密码加密盐值',
    `password`    varchar(255)    NOT NULL COMMENT '密码',
    `nick_name`   varchar(500)    NOT NULL COMMENT '昵称',
    `phone_num`   varchar(45)              DEFAULT NULL COMMENT '手机号',
    `email`       varchar(500)    NOT NULL COMMENT '邮箱',
    `is_locked`   tinyint(1)      NOT NULL DEFAULT '0' COMMENT '锁定标识 1-是 0-否',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)      NOT NULL DEFAULT '0' COMMENT '删除标识 1-是 0-否',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='博客用户';