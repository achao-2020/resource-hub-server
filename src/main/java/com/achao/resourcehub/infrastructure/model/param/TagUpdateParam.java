package com.achao.resourcehub.infrastructure.model.param;

import com.achao.resourcehub.infrastructure.entity.Tag;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import lombok.Data;

@Data
public class TagUpdateParam {
    private Long id;
    private String name;
    private String type;

    public static Tag toTag(TagUpdateParam updateParam) {
        if (updateParam == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(updateParam.getId());
        tag.setName(updateParam.getName());
        tag.setType(updateParam.getType());
        return tag;
    }

    public void validate() {
        AssertionUtil.assertNotNull(id, "id不能为空");
    }
}