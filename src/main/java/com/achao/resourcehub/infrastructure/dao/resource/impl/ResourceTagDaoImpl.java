package com.achao.resourcehub.infrastructure.dao.resource.impl;

import com.achao.resourcehub.infrastructure.dao.resource.ResourceTagDao;
import com.achao.resourcehub.infrastructure.dao.resource.mapper.ResourceTagMapper;
import com.achao.resourcehub.infrastructure.entity.ResourceTag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ResourceTagDaoImpl extends ServiceImpl<ResourceTagMapper, ResourceTag> implements ResourceTagDao {
    @Override
    public List<ResourceTag> queryByTagId(Long tagId) {
        AssertionUtil.assertNotNull(tagId, "tagId不能为空");
        return super.list(Wrappers.<ResourceTag>lambdaQuery().eq(ResourceTag::getTagId, tagId));
    }
}
