package com.achao.resourcehub.controller.resource.param;

import com.achao.resourcehub.infrastructure.entity.Resource;
import lombok.Data;

@Data
public class ResourceUpdateParam {
    private Long id;
    private String name;
    private String type;
    private String description;
    private String shareLink;

    public static Resource toResource(ResourceUpdateParam updateParam) {
        Resource resource = new Resource();
        resource.setId(updateParam.getId());
        resource.setName(updateParam.getName());
        resource.setType(updateParam.getType());
        resource.setDescription(updateParam.getDescription());
        resource.setShareLink(updateParam.getShareLink());
        return resource;
    }
}
