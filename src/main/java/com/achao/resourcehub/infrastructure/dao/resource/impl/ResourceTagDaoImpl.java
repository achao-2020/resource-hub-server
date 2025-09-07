package com.achao.resourcehub.infrastructure.dao.resource.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.achao.resourcehub.controller.resource.param.ResourceTagSaveParam;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceTagDao;
import com.achao.resourcehub.infrastructure.dao.resource.mapper.ResourceTagMapper;
import com.achao.resourcehub.infrastructure.entity.ResourceTag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ResourceTagDaoImpl extends ServiceImpl<ResourceTagMapper, ResourceTag> implements ResourceTagDao {
    @Override
    public List<ResourceTag> queryByTagId(Long tagId) {
        AssertionUtil.assertNotNull(tagId, "tagId不能为空");
        return super.list(Wrappers.<ResourceTag>lambdaQuery().eq(ResourceTag::getTagId, tagId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(List<Long> resourceIds, Long tagId) {
        AssertionUtil.assertNotEmpty(resourceIds, "resourceIds不能为空");
        AssertionUtil.assertNotNull(tagId, "tagId不能为空");
        List<ResourceTag> resourceTags = resourceIds.stream().map(r -> {
            ResourceTag resourceTag = new ResourceTag();
            resourceTag.setResourceId(r);
            resourceTag.setTagId(tagId);
            return resourceTag;
        }).collect(Collectors.toList());
        return super.saveBatch(resourceTags);
    }

    @Override
    public List<ResourceTag> queryByResourceIds(List<Long> resourceIds) {
        LambdaQueryWrapper<ResourceTag> wrapper = Wrappers.<ResourceTag>lambdaQuery().in(ResourceTag::getResourceId, resourceIds);
        return this.list(wrapper);
    }

    @Override
    public void save(ResourceTagSaveParam resourceTagSaveParam) {
        AssertionUtil.assertNotNull(resourceTagSaveParam, "参数不能为空");
        resourceTagSaveParam.validate();
        // 存在的过滤
        List<ResourceTag> existResourceTags = this.list(Wrappers.<ResourceTag>lambdaQuery().eq(ResourceTag::getResourceId, resourceTagSaveParam.getResourceId()).in(ResourceTag::getTagId, resourceTagSaveParam.getTagIds()));
        Set<Long> existTagIdSet = CollectionUtil.emptyIfNull(existResourceTags).stream().map(ResourceTag::getTagId).collect(Collectors.toSet());
        List<ResourceTag> resourceTags = resourceTagSaveParam.getTagIds().stream().filter(t -> !existTagIdSet.contains(t)).map(t -> {
            ResourceTag resourceTag = new ResourceTag();
            resourceTag.setResourceId(resourceTagSaveParam.getResourceId());
            resourceTag.setTagId(t);
            return resourceTag;
        }).collect(Collectors.toList());
        super.saveBatch(resourceTags);
    }
}
