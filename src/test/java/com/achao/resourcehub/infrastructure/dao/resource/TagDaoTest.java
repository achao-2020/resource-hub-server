package com.achao.resourcehub.infrastructure.dao.resource;

import com.achao.resourcehub.infrastructure.model.enums.TagTypeEnum;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TagDaoTest {

    @Autowired
    private TagDao tagDao;

    @Test
    void insert() {
        TagSaveParam tag = new TagSaveParam();
        tag.setName(TagTypeEnum.ALIPAN.getName());
        tag.setType(TagTypeEnum.ALIPAN.name());
        boolean save = tagDao.save(tag);
        assertTrue(save);
    }
}