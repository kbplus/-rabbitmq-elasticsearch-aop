package com.kbplus.demo.common.enums;
/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
public enum LogType {

    LOGIN("登录"),
    EXIT("退出"),
    ADD("新增"),
    UPDATE("修改"),
    DELETE("删除"),
    SELECT("查询"),
    OPERATION("操作"),
    UPLOAD("上传"),
    DOWNLOAD("下载"),
    IMPORT("导入"),
    EXPORT("导出"),
    ;


    private final String name;

    LogType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
