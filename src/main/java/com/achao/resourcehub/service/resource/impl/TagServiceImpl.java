package com.achao.resourcehub.service.resource.impl;

import com.achao.resourcehub.controller.resource.vo.TagQueryVo;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceTagDao;
import com.achao.resourcehub.infrastructure.dao.resource.TagDao;
import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagResourceParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.service.resource.TagService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;
    @Resource
    private ResourceTagDao resourceTagDao;

    @Override
    public boolean saveTag(TagSaveParam saveParam) {
        AssertionUtil.assertNotNull(saveParam, "参数不能为空");
        saveParam.validate();
        return tagDao.save(saveParam);
    }

    @Override
    public boolean updateTag(TagUpdateParam updateParam) {
        AssertionUtil.assertNotNull(updateParam, "参数不能为空");
        updateParam.validate();
        return tagDao.updateById(updateParam);
    }

    @Override
    public boolean deleteTag(Long id) {
        return tagDao.removeById(id);
    }

    @Override
    public TagQueryVo getTagById(Long id) {
        return TagQueryVo.convertFrom(tagDao.getById(id));
    }

    @Override
    public PageResult<TagQueryVo> page(PageQuery<TagPageQueryParam> queryParam) {
        return PageResult.convertFrom(tagDao.page(queryParam), TagQueryVo.class);
    }

    @Override
    public Boolean tagResource(TagResourceParam tagResourceParam) {
        AssertionUtil.assertNotNull(tagResourceParam, "参数不能为空");
        tagResourceParam.validate();
        Long tagId = tagResourceParam.getTagId();
        Tag tag = tagDao.queryById(tagId);
        AssertionUtil.assertNotNull(tag, "标签不存在");
        return resourceTagDao.saveBatch(tagResourceParam.getResourceIds(), tagId);
    }
}