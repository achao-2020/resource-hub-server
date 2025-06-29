package com.achao.resourcehub.service.resource.impl;

import cn.hutool.core.util.ObjectUtil;
import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.controller.resource.vo.ResourceQueryVo;
import com.achao.resourcehub.controller.resource.vo.TagQueryVo;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceDao;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceTagDao;
import com.achao.resourcehub.infrastructure.dao.resource.TagDao;
import com.achao.resourcehub.infrastructure.entity.ResourceTag;
import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.service.resource.ResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private ResourceDao resourceDao;
    @Resource
    private ResourceTagDao resourceTagDao;
    @Resource
    private TagDao tagDao;

    @Override
    public boolean saveResource(ResourceSaveParam saveParam) {
        AssertionUtil.assertNotNull(saveParam, "参数不能为空");
        saveParam.validate();
        return resourceDao.save(saveParam);
    }

    @Override
    public boolean updateResource(ResourceUpdateParam updateParam) {
        return resourceDao.update(updateParam);
    }

    @Override
    public boolean deleteResource(Long id) {
        return resourceDao.removeById(id);
    }

    @Override
    public ResourceQueryVo getResourceById(Long id) {
        return ResourceQueryVo.convertFrom(resourceDao.getById(id));
    }

    @Override
    public PageResult<ResourceQueryVo> page(PageQuery<ResourceQueryParam> pageQuery) {
        PageResult<ResourceQueryVo> pageResult = PageResult.convertFrom(resourceDao.page(pageQuery), ResourceQueryVo.class);
        if (pageQuery.getParam() != null && Boolean.TRUE.equals(pageQuery.getParam().getNeedTag()) && ObjectUtil.isNotEmpty(pageResult.getRecords())) {
            this.fillTagInfo(pageResult);
        }
        return pageResult;
    }

    private void fillTagInfo(PageResult<ResourceQueryVo> pageResult) {
        List<ResourceQueryVo> records = pageResult.getRecords();
        List<ResourceTag> resourceTags = resourceTagDao.queryByResourceIds(records.stream().map(ResourceQueryVo::getId).collect(Collectors.toList()));
        if (ObjectUtil.isNotEmpty(resourceTags)) {
            // 根据resourceId分组
            Map<Long, List<ResourceTag>> groupByResourceIdMap = resourceTags.stream().collect(Collectors.groupingBy(ResourceTag::getResourceId));
            List<Tag> tags = tagDao.queryByIds(resourceTags.stream().map(ResourceTag::getTagId).distinct().collect(Collectors.toList()));
            Map<Long, Tag> tagIdMap = tags.stream().collect(Collectors.toMap(Tag::getId, t -> t));
            for (ResourceQueryVo record : records) {
                List<ResourceTag> resTags = groupByResourceIdMap.get(record.getId());
                if (ObjectUtil.isEmpty(resTags)) {
                    continue;
                }
                record.setTagVos(resTags.stream().filter(rt -> tagIdMap.containsKey(rt.getTagId()))
                        .map(rt -> TagQueryVo.convertFrom(tagIdMap.get(rt.getTagId()))).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public PageResult<ResourceQueryVo> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery) {
        AssertionUtil.assertNotNull(pageQuery, "参数不能为空");
        AssertionUtil.assertNotNull(pageQuery.getParam(), "pageQuery参数不能为空");
        AssertionUtil.assertNotBlank(pageQuery.getParam().getTagName(), "tagName参数不能为空");
        return PageResult.convertFrom(resourceDao.pageByTag(pageQuery), ResourceQueryVo.class);
    }
}