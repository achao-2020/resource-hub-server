package com.achao.resourcehub.controller.resource.param;

import lombok.Data;

@Data
public class ResourceQueryParam {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Boolean needTag;
}
