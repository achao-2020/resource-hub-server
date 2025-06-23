package com.achao.resourcehub.infrastructure.model.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * alipan-阿里云盘
 */
@Getter
@Slf4j
public enum TagTypeEnum {
    ALIPAN("阿里云盘"),
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
