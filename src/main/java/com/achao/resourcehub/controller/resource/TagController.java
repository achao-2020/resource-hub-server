package com.achao.resourcehub.controller.resource;

import com.achao.resourcehub.api.TagApi;
import com.achao.resourcehub.controller.resource.vo.TagQueryVo;
import com.achao.resourcehub.infrastructure.model.param.TagResourceParam;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.infrastructure.model.res.ResResult;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;
import com.achao.resourcehub.service.resource.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagController implements TagApi {

    private final TagService tagService;

    @Override
    public ResResult<Boolean> save(TagSaveParam saveParam) {
        return ResResult.success(tagService.saveTag(saveParam));
    }

    @Override
    public ResResult<Boolean> update(TagUpdateParam updateParam) {
        return ResResult.success(tagService.updateTag(updateParam));
    }

    @Override
    public ResResult<Boolean> delete(Long id) {
        return ResResult.success(tagService.deleteTag(id));
    }

    @Override
    public ResResult<TagQueryVo> getById(Long id) {
        return ResResult.success(tagService.getTagById(id));
    }

    @Override
    public ResResult<PageResult<TagQueryVo>> page(PageQuery<TagPageQueryParam> pageQuery) {
        return ResResult.success(tagService.page(pageQuery));
    }

    @Override
    public ResResult<Boolean> tagResource(@RequestBody TagResourceParam tagResourceParam) {
        return ResResult.success(tagService.tagResource(tagResourceParam));
    }
}