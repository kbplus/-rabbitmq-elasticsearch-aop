package com.kbplus.demo.common.entity;

public class PageRequest {
    private static final long serialVersionUID = 1L;
    private final Integer page;
    private final Integer size;

    public PageRequest(Integer page, Integer size) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

}
