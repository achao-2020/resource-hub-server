package com.achao.resourcehub.infrastructure.model.enums;

import com.achao.resourcehub.infrastructure.exception.BizException;
import lombok.Getter;

/**
 * 资源类型枚举
 * 电影、电视剧、动漫、综艺、纪录片、电影片段、音乐、游戏、软件、工具、其他
 */
@Getter
public enum ResourceTypeEnum {
    MOVIE("电影"),
    TV("电视剧"),
    ANIMATION("动漫"),
    VARIETY("综艺"),
    DOCUMENTARY("纪录片"),
    MOVIE_CLIP("电影片段"),
    MUSIC("音乐"),
    GAME("游戏"),
    SOFTWARE("软件"),
    TOOL("工具"),
    OTHER("其他");
    private final String name;
    ResourceTypeEnum(String name) {
        this.name = name;
    }

    public static void isExist(String type) {
        for (ResourceTypeEnum value : ResourceTypeEnum.values()) {
            if (value.name.equals(type)) {
                return;
            }
        }
        throw new BizException("资源类型不存在:" + type);
    }
}
