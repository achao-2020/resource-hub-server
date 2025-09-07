package com.achao.resourcehub.controller.resource.param;

import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import lombok.Data;

import java.util.List;

@Data
public class ResourceTagSaveParam {
    private Long resourceId;

    private List<Long> tagIds;

    public void validate() {
        AssertionUtil.assertNotNull(resourceId, "资源id不能为空");
        AssertionUtil.assertNotEmpty(tagIds, "标签id不能为空");
    }
}
