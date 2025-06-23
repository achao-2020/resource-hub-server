package com.achao.resourcehub.infrastructure.model.param;

import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.achao.resourcehub.infrastructure.model.enums.TagTypeEnum;
import lombok.Data;

@Data
public class TagSaveParam {
    private String name;
    private String type;

    public static Tag toTag(TagSaveParam saveParam) {
        if (saveParam == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setName(saveParam.getName());
        tag.setType(saveParam.getType());
        return tag;
    }

    public void validate() {
        AssertionUtil.assertNotBlank(name, "标签名称不能为空");
        AssertionUtil.assertNotBlank(type, "标签类型不能为空");
        AssertionUtil.assertTrue(TagTypeEnum.isExist(type), "标签类型不存在");
    }
}