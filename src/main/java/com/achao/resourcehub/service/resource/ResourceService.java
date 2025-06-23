package com.achao.resourcehub.service.resource;

import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.controller.resource.vo.ResourceQueryVo;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;

public interface ResourceService {
    boolean saveResource(ResourceSaveParam saveParam);
    boolean updateResource(ResourceUpdateParam updateParam);
    boolean deleteResource(Long id);
    ResourceQueryVo getResourceById(Long id);
    PageResult<ResourceQueryVo> page(PageQuery<ResourceQueryParam> pageQuery);
    PageResult<ResourceQueryVo> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery);
}