-- -----------------------------
-- blog_article
-- -----------------------------
insert into blog_article(title, user_id, subtitle, content, category_id)
values ('title001', 1, 'subtitile001', 'contextcontextcontextcontext', 1),
       ('title002', 1, 'subtitle002', 'contextcontextcontextcontext', 2),
       ('title003', 1, null, 'contextcontextcontextcontext', 3);

-- -----------------------------
-- blog_category
-- -----------------------------
insert into blog_category(category_name, category_order)
VALUES ('技术', 1), ('算法', 2), ('项目', 3), ('工具', 4);

-- -----------------------------
-- blog_tag
-- -----------------------------
insert into blog_tag(tag_name)
VALUES ('java'), ('oracle'), ('swagger-editor');

-- -----------------------------
-- blog_article_tag
-- -----------------------------
insert into blog_article_tag(article_id, tag_id)
VALUES (1, 1), (1, 2), (2, 2), (2, 3);

-- -----------------------------
-- blog_user
-- -----------------------------
insert into blog_user(username, secret_key, password, nick_name, phone_num, email)
values ('liqi', 'test-secret-key', 'test-password', 'Q_L', null, '123123@qq.com');