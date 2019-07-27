package com.study.spring.batch.cos.constant;

public abstract class Constants {

    public static final int START_METHOD_ORDER = 10;
    public static final int INIT_METHOD_ORDER = 20;
    public static final int PROCESSDATA_METHOD_ORDER = 30;
    public static final int COMPRESS_METHOD_ORDER = 40;
    public static final int UNLOAD_METHOD_ORDER = 50;
    public static final int FINISH_METHOD_ORDER = 60;
    
    public static final String START_METHOD_NAME = "START";
    public static final String INIT_METHOD_NAME = "INIT";
    public static final String PROCESSDATA_METHOD_NAME = "PROCESSDATA";
    public static final String COMPRESS_METHOD_NAME = "COMPRESS";
    public static final String UNLOAD_METHOD_NAME = "UPLOAD";
    public static final String FINISH_METHOD_NAME = "FINISHED";
}
