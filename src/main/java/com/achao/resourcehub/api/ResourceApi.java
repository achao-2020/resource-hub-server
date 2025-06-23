package com.achao.resourcehub.api;

import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.param.ResourceTagQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceUpdateParam;
import com.achao.resourcehub.controller.resource.vo.ResourceQueryVo;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.infrastructure.model.res.ResResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/resources")
public interface ResourceApi {

    @PostMapping
    ResResult<Boolean> save(@RequestBody ResourceSaveParam saveParam);

    @PutMapping
    ResResult<Boolean> update(@RequestBody ResourceUpdateParam updateParam);

    @DeleteMapping("/{id}")
    ResResult<Boolean> delete(@PathVariable Long id);

    @GetMapping("/{id}")
    ResResult<ResourceQueryVo> getById(@PathVariable Long id);

    @GetMapping
    ResResult<PageResult<ResourceQueryVo>> page(PageQuery<ResourceQueryParam> pageQuery);

    @GetMapping("/by-tag")
    ResResult<PageResult<ResourceQueryVo>> pageByTag(PageQuery<ResourceTagQueryParam> pageQuery);
}