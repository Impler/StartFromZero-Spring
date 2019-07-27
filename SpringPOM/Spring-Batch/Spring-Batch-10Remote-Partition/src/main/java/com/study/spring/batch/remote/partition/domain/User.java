package com.study.spring.batch.remote.partition.domain;

public class User {
    private int id;
    private String name;
    private String certNo;
    private String resultCode;
    private String resultContent;

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

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", certNo=" + certNo + ", resultCode=" + resultCode
                + ", resultContent=" + resultContent + "]";
    }

}
