package com.achao.resourcehub.infrastructure.exception;

import cn.hutool.core.util.ObjectUtil;

import java.util.Collection;

public class AssertionUtil {
    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            throw new BizException(message);
        }
    }

    public static void assertFalse(boolean expression, String message) {
        if (expression) {
            throw new BizException(message);
        }
    }

    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new BizException(message);
        }
    }

    public static void assertNull(Object object, String message) {
        if (object != null) {
            throw new BizException(message);
        }
    }

    public static void assertBlank(String str, String message) {
        if (ObjectUtil.isNotEmpty(str)) {
            throw new BizException(message);
        }
    }

    public static void assertNotBlank(String str, String message) {
        if (ObjectUtil.isEmpty(str)) {
            throw new BizException(message);
        }
    }

    public static void assertEmpty(Collection<?> collection, String message) {
        if (ObjectUtil.isNotEmpty(collection)) {
            throw new BizException(message);
        }
    }

    public static void assertNotEmpty(Collection<?> collection, String message) {
        if (ObjectUtil.isEmpty(collection)) {
            throw new BizException(message);
        }
    }
}
