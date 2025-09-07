package com.achao.resourcehub.infrastructure.model.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义标签
 */
@Getter
@Slf4j
public enum TagTypeEnum {
    CUSTOM("自定义"),
    ;
    private final String name;

    TagTypeEnum(String name) {
        this.name = name;
    }

    public static boolean isExist(String type) {
        for (TagTypeEnum value : TagTypeEnum.values()) {
            if (value.name().equals(type)) {
                return true;
            }
        }
        log.warn("不存在的tag类型: {}", type);
        return false;
    }

}
