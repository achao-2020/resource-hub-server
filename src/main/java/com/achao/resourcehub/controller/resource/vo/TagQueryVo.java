package com.achao.resourcehub.controller.resource.vo;

import com.achao.resourcehub.infrastructure.entity.Tag;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagQueryVo {
    private Long id;
    private String name;
    private String type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static TagQueryVo convertFrom(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagQueryVo tagQueryVo = new TagQueryVo();
        tagQueryVo.setId(tag.getId());
        tagQueryVo.setName(tag.getName());
        tagQueryVo.setType(tag.getType());
        tagQueryVo.setCreateTime(tag.getCreateTime());
        tagQueryVo.setUpdateTime(tag.getUpdateTime());
        return tagQueryVo;
    }
}
