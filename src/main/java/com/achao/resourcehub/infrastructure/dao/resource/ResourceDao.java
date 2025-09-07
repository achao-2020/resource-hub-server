package com.achao.resourcehub.infrastructure.dao.resource;

import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.infrastructure.entity.Resource;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface ResourceDao {
    Resource save(ResourceSaveParam saveParam);

    boolean update(ResourceUpdateParam updateParam);

    boolean removeById(Long id);

    Resource getById(Long id);

    Page<Resource> page(PageQuery<ResourceQueryParam> pageQuery);

    Page<Resource> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery);
}