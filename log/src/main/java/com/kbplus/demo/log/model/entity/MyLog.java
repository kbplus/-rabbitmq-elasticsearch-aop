package com.kbplus.demo.log.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Data
@Document(indexName = "my_log")
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Field(type = FieldType.Keyword)
    private Date recordTime;

}
