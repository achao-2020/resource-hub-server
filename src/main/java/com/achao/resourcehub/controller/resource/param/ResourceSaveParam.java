package com.achao.resourcehub.controller.resource.param;

import com.achao.resourcehub.infrastructure.entity.Resource;
import com.achao.resourcehub.infrastructure.exception.AssertionUtil;
import com.achao.resourcehub.infrastructure.model.enums.ResourceTypeEnum;
import lombok.Data;

@Data
public class ResourceSaveParam {
    private String name;
    private String type;
    private String description;
    private String shareLink;

    public static Resource toResource(ResourceSaveParam saveParam) {
        Resource resource = new Resource();
        resource.setName(saveParam.getName());
        resource.setType(saveParam.getType());
        resource.setDescription(saveParam.getDescription());
        resource.setShareLink(saveParam.getShareLink());
        resource.setValid(Boolean.TRUE);
        return resource;
    }

    public void validate() {
        AssertionUtil.assertNotBlank(name, "资源名称不能为空");
        AssertionUtil.assertNotBlank(type, "资源类型不能为空");
        ResourceTypeEnum.isExist(type);
        AssertionUtil.assertNotBlank(shareLink, "资源分享链接不能为空");
        AssertionUtil.assertNotBlank(description, "资源描述不能为空");
    }
}
