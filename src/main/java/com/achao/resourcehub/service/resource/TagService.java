package com.achao.resourcehub.service.resource;

import com.achao.resourcehub.controller.resource.vo.TagQueryVo;
import com.achao.resourcehub.infrastructure.model.param.TagResourceParam;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;

public interface TagService {
    boolean saveTag(TagSaveParam saveParam);
    boolean updateTag(TagUpdateParam updateParam);
    boolean deleteTag(Long id);
    TagQueryVo getTagById(Long id);
    PageResult<TagQueryVo> page(PageQuery<TagPageQueryParam> queryParam);

    Boolean tagResource(TagResourceParam tagResourceParam);
}