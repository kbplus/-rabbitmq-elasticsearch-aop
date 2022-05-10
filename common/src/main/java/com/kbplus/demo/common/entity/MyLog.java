package com.kbplus.demo.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Data
public class MyLog {

    private String id;

    private String moduleName;

    private String url;

    private String ip;

    private String methodType;

    private String comment;

    private String type;

    private Object result;

    private Long cost;

    private List<String> params;

}
