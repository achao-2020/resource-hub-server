package com.achao.resourcehub.infrastructure.dao.resource.impl;

import cn.hutool.core.util.ObjectUtil;
import com.achao.resourcehub.infrastructure.dao.resource.TagDao;
import com.achao.resourcehub.infrastructure.dao.resource.mapper.TagMapper;
import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.achao.resourcehub.infrastructure.exception.BizException;
import com.achao.resourcehub.infrastructure.model.param.TagPageQueryParam;
import com.achao.resourcehub.infrastructure.model.param.TagSaveParam;
import com.achao.resourcehub.infrastructure.model.param.TagUpdateParam;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class TagDaoImpl implements TagDao {
    @Resource
    private TagMapper tagMapper;
    @Override
    public Tag queryByTagName(String tagName) {
        return tagMapper.selectOne(Wrappers.<Tag>lambdaQuery().eq(Tag::getName, tagName));
    }

    @Override
    public boolean save(TagSaveParam saveParam) {
        AssertionUtil.assertNotNull(saveParam, "参数不能为空");
        saveParam.validate();
        // 唯一性校验
        if (queryByTagName(saveParam.getName()) != null) {
            throw new BizException(saveParam.getName() + "标签已存在");
        }
        Tag tag = TagSaveParam.toTag(saveParam);
        return tagMapper.insert(tag) > 0;
    }

    @Override
    public boolean updateById(TagUpdateParam updateParam) {
        AssertionUtil.assertNotNull(updateParam, "参数不能为空");
        updateParam.validate();
        Tag tag = TagUpdateParam.toTag(updateParam);
        return tagMapper.updateById(tag) > 0;
    }

    @Override
    public boolean removeById(Long id) {
        AssertionUtil.assertNotNull(id, "参数不能为空");
        return tagMapper.deleteById(id) > 0;
    }

    @Override
    public Tag getById(Long id) {
        AssertionUtil.assertNotNull(id, "参数不能为空");
        return tagMapper.selectById(id);
    }

    @Override
    public Page<Tag> page(PageQuery<TagPageQueryParam> queryParam) {
        Page page = queryParam.convertToPage();
        LambdaQueryWrapper<Tag> wrapper = buildQueryWrapper(queryParam.getParam());
        return tagMapper.selectPage(page, wrapper);
    }

    private LambdaQueryWrapper<Tag> buildQueryWrapper(TagPageQueryParam param) {
        LambdaQueryWrapper<Tag> wrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(param)) {
            wrapper.eq(ObjectUtil.isNotNull(param.getId()), Tag::getId, param.getId())
                    .likeRight(ObjectUtil.isNotEmpty(param.getName()), Tag::getName, param.getName())
                    .eq(ObjectUtil.isNotEmpty(param.getType()), Tag::getType, param.getType());
        }
        return wrapper;
    }
}
