package com.achao.resourcehub.infrastructure.model.res;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PageQuery<T> {
    private final Integer pageNum;
    private final Integer pageSize;
    private T param;
    public PageQuery(Integer pageNum, Integer pageSize, T param) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.param = param;
    }

    public PageQuery(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageQuery() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public Page convertToPage() {
        return new Page(pageNum, pageSize);
    }
}
