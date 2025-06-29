package com.achao.resourcehub.controller.resource.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.achao.resourcehub.infrastructure.entity.Resource;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResourceQueryVo {
    private Long id;
    private String name;
    private String type;
    private String description;
    private String shareLink;
    private Boolean valid;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<TagQueryVo> tagVos;

    public static ResourceQueryVo convertFrom(Resource resource) {
        return BeanUtil.copyProperties(resource, ResourceQueryVo.class);
    }

    public void interceptDesc() {
        // 如果描述的字符数量超过100，则截取100个字符，剩余用 ... 表示
        if (ObjectUtil.isNotEmpty(this.getDescription()) && this.getDescription().length() > 100) {
            this.setDescription(this.getDescription().substring(0, 100) + "...");
        }
    }
}
