package com.study.spring.batch.remote.partition.domain;

import com.study.spring.batch.remote.partition.domain.dto.Pagination;

public class DataExtStatus {
    // 主键
    private int id;
    // 数据日期
    private String dataDate;
    // 已分配数量
    private int assignCount;
    // 并发控制
    private int version = 0;

    private Pagination page;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public int getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(int assignCount) {
        this.assignCount = assignCount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void incrementVersion() {
        this.version++;
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "DataExtStatus [id=" + id + ", dataDate=" + dataDate + ", assignCount=" + assignCount + ", version="
                + version + ", page=" + page + "]";
    }

    
}
