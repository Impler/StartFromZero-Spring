package com.study.spring.batch.cos.domain;

public class CosData {

    private int id;
    private String name;
    private boolean isDealed = false;

    public CosData() {
        super();
    }

    public CosData(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDealed() {
        return isDealed;
    }

    public void setDealed(boolean isDealed) {
        this.isDealed = isDealed;
    }

    @Override
    public String toString() {
        return "CosData [id=" + id + ", name=" + name + ", isDealed=" + isDealed + "]";
    }

}
