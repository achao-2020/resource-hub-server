package com.achao.resourcehub.infrastructure.model.res;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> records;
    private long total;
    private long size;

    public static <T> PageResult<T> convertFrom(Page page, Class<T> tClass) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(BeanUtil.copyToList(page.getRecords(), tClass));
        pageResult.setTotal(page.getTotal());
        pageResult.setSize(page.getSize());
        return pageResult;
    }
}
