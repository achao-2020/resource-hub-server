package com.achao.resourcehub.controller.resource;

import com.achao.resourcehub.api.ResourceApi;
import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.controller.resource.vo.ResourceQueryVo;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.infrastructure.model.res.ResResult;
import com.achao.resourcehub.service.resource.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController implements ResourceApi {

    private final ResourceService resourceService;

    @Override
    public ResResult<Boolean> save(@RequestBody ResourceSaveParam saveParam) {
        return ResResult.success(resourceService.saveResource(saveParam));
    }

    @Override
    public ResResult<Boolean> update(@RequestBody ResourceUpdateParam updateParam) {
        return ResResult.success(resourceService.updateResource(updateParam));
    }

    @Override
    public ResResult<Boolean> delete(@PathVariable Long id) {
        return ResResult.success(resourceService.deleteResource(id));
    }

    @Override
    public ResResult<ResourceQueryVo> getById(@PathVariable Long id) {
        return ResResult.success(resourceService.getResourceById(id));
    }

    @Override
    public ResResult<PageResult<ResourceQueryVo>> page(PageQuery<ResourceQueryParam> pageQuery) {
        return ResResult.success(resourceService.page(pageQuery));
    }

    @Override
    public ResResult<PageResult<ResourceQueryVo>> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery) {
        return ResResult.success(resourceService.pageByTag(pageQuery));
    }
}