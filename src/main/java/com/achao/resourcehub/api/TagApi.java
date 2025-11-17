package com.achao.resourcehub.api;

import com.achao.resourcehub.controller.resource.vo.TagQueryVo;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagResourceParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.infrastructure.model.res.ResResult;
import org.springframework.web.bind.annotation.*;
// Film
@RequestMapping("/api/tags")
public interface TagApi {

    @PostMapping
    ResResult<Boolean> save(@RequestBody TagSaveParam saveParam);

    @PutMapping
    ResResult<Boolean> update(@RequestBody TagUpdateParam updateParam);

    @DeleteMapping("/{id}")
    ResResult<Boolean> delete(@PathVariable Long id);

    @GetMapping("/{id}")
    ResResult<TagQueryVo> getById(@PathVariable Long id);

    @PostMapping("/page")
    ResResult<PageResult<TagQueryVo>> page(@RequestBody PageQuery<TagPageQueryParam> pageQuery);

    /**
     * 对资源进行打标签
     */
    @PostMapping("/tag-resource")
    ResResult<Boolean> tagResource(@RequestBody TagResourceParam tagResourceParam);
}