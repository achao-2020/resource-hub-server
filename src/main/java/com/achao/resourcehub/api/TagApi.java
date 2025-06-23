package com.achao.resourcehub.api;

import com.achao.resourcehub.controller.resource.vo.TagQueryVo;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.infrastructure.model.res.ResResult;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;
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

    @GetMapping
    ResResult<PageResult<TagQueryVo>> page(@RequestBody PageQuery<TagPageQueryParam> pageQuery);
}