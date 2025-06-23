package com.achao.resourcehub.service.resource.impl;

import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.controller.resource.vo.ResourceQueryVo;
import com.achao.resourcehub.infrastructure.dao.resource.ResourceDao;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.service.resource.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private ResourceDao resourceDao;

    @Override
    public boolean saveResource(ResourceSaveParam saveParam) {
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
        return PageResult.convertFrom(resourceDao.page(pageQuery), ResourceQueryVo.class);
    }

    @Override
    public PageResult<ResourceQueryVo> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery) {
        AssertionUtil.assertNotNull(pageQuery, "参数不能为空");
        AssertionUtil.assertNotNull(pageQuery.getParam(), "pageQuery参数不能为空");
        AssertionUtil.assertNotBlank(pageQuery.getParam().getTagName(), "tagName参数不能为空");
        return PageResult.convertFrom(resourceDao.pageByTag(pageQuery), ResourceQueryVo.class);
    }
}