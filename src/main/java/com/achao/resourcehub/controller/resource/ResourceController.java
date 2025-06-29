package com.achao.resourcehub.controller.resource;

import cn.hutool.core.util.ObjectUtil;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        PageResult<ResourceQueryVo> page = resourceService.page(pageQuery);
        if (ObjectUtil.isNotEmpty(page.getRecords())) {
            for (ResourceQueryVo record : page.getRecords()) {
                record.interceptDesc();
            }
        }
        return ResResult.success(page);
    }

    @Override
    public ResResult<PageResult<ResourceQueryVo>> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery) {
        return ResResult.success(resourceService.pageByTag(pageQuery));
    }
}