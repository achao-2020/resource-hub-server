package com.achao.resourcehub.service.resource;

import com.achao.resourcehub.SpringBaseTest;
import com.achao.resourcehub.infrastructure.model.param.TagResourceParam;
import jakarta.annotation.Resource;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagServiceTest extends SpringBaseTest {
    @Resource
    private TagService tagService;

    @Test
    void tagResource() {
        TagResourceParam param = new TagResourceParam();
        param.setResourceIds(Lists.list(1L));
        param.setTagId(1L);
        tagService.tagResource(param);
    }
}