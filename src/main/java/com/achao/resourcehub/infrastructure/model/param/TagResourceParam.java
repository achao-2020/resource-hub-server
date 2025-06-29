package com.achao.resourcehub.infrastructure.model.param;

import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import lombok.Data;

import java.util.List;

@Data
public class TagResourceParam {
    private Long tagId;

    private List<Long> resourceIds;

    public void validate() {
        AssertionUtil.assertNotNull(tagId, "标签ID不能为空");
        AssertionUtil.assertNotEmpty(resourceIds, "资源ID列表不能为空");
    }
}
