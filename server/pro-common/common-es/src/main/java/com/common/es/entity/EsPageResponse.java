package com.common.es.entity;

import lombok.Data;

import java.util.List;

@Data
public class EsPageResponse<T> {

    // 数据
    private List<T> datas;

    // 总数
    private long totalCount;

    // 页号
    private int pageNum;

    // 页显示数量
    private int pageSize;

    public int getTotalPage() {
        int totalPage = 0;
        if (pageSize < 1) {
            pageSize = 20;
        }
        if (totalCount > 0) {
            totalPage = (int) (totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0));
        }
        return totalPage;
    }
}
