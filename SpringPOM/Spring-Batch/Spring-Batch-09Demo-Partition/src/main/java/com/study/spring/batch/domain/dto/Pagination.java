package com.study.spring.batch.domain.dto;

public class Pagination {

    private int startId;
    private int endId;
    private int dataCount;
    private int lastId = -1;

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    @Override
    public String toString() {
        return "Pagination [startId=" + startId + ", endId=" + endId + ", dataCount=" + dataCount + "]";
    }

}
