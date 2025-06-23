package com.achao.resourcehub.infrastructure.dao.resource;

import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface TagDao {
    Tag queryByTagName(String tagName);

    boolean save(TagSaveParam saveParam);

    boolean updateById(TagUpdateParam updateParam);

    boolean removeById(Long id);

    Tag getById(Long id);

    Page<Tag> page(PageQuery<TagPageQueryParam> queryParam);
}
