package com.achao.resourcehub.infrastructure.dao.resource;

import com.achao.resourcehub.infrastructure.entity.ResourceTag;

import java.util.List;

public interface ResourceTagDao {
    List<ResourceTag> queryByTagId(Long tagId);

    boolean saveBatch(List<Long> resourceIds, Long tagId);

    List<ResourceTag> queryByResourceIds(List<Long> resourceIds);
}
