package com.achao.resourcehub.infrastructure.dao.resource.impl;

import cn.hutool.core.util.ObjectUtil;
import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceDao;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceTagDao;
import com.achao.resourcehub.infrastructure.dao.resource.TagDao;
import com.achao.resourcehub.infrastructure.dao.resource.mapper.ResourceMapper;
import com.achao.resourcehub.infrastructure.entity.Resource;
import com.achao.resourcehub.infrastructure.entity.ResourceTag;
import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
@Slf4j
public class ResourceDaoImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceDao {
    private final TagDao tagDao;

    private final ResourceTagDao resourceTagDao;

    @Override
    public boolean save(ResourceSaveParam saveParam) {
        Resource resource = ResourceSaveParam.toResource(saveParam);
        return save(resource);
    }

    @Override
    public boolean update(ResourceUpdateParam updateParam) {
        Resource resource = ResourceUpdateParam.toResource(updateParam);
        return updateById(resource);
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    public Resource getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Page<Resource> page(PageQuery<ResourceQueryParam> pageQuery) {
        Page page = pageQuery.convertToPage();
        return super.page(page,buildQueryWrapper(pageQuery.getParam()));
    }

    @Override
    public Page<Resource> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery) {
        ResourceTagQueryParam param = pageQuery.getParam();
        String tagName = param.getTagName();
        Tag tag = tagDao.queryByTagName(tagName);
        if (tag == null) {
            log.info("tagName:{}不存在", tagName);
            return Page.of(pageQuery.getPageNum(), pageQuery.getPageSize());
        }
        Long tagId = tag.getId();
        List<ResourceTag> resourceTags = resourceTagDao.queryByTagId(tagId);
        if (resourceTags.isEmpty()) {
            log.info("tagName:{}没有关联资源", tagName);
            return Page.of(pageQuery.getPageNum(), pageQuery.getPageSize());
        }
        List<Long> resourceIds = resourceTags.stream().map(ResourceTag::getResourceId).collect(Collectors.toList());
        return super.page(pageQuery.convertToPage(), new LambdaQueryWrapper<Resource>().in(Resource::getId, resourceIds));
    }

    private LambdaQueryWrapper<Resource> buildQueryWrapper(ResourceQueryParam param) {
        LambdaQueryWrapper<Resource> queryWrapper = Wrappers.<Resource>lambdaQuery();
        if (ObjectUtil.isNotNull(param)) {
            queryWrapper.eq(ObjectUtil.isNotNull(param.getId()), Resource::getId, param.getId())
                    .eq(ObjectUtil.isNotEmpty(param.getName()), Resource::getName, param.getName())
                    .eq(ObjectUtil.isNotEmpty(param.getType()), Resource::getType, param.getType());
        }
        return queryWrapper;
    }
}
