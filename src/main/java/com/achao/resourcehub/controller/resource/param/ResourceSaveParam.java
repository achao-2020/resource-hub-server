package com.achao.resourcehub.controller.resource.param;

import com.achao.resourcehub.infrastructure.entity.Resource;
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
}
