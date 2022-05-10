package com.kbplus.demo.log.controller;

import com.kbplus.demo.common.annotation.SystemLog;
import com.kbplus.demo.common.entity.Page;
import com.kbplus.demo.common.entity.PageRequest;
import com.kbplus.demo.common.enums.LogType;
import com.kbplus.demo.log.model.entity.MyLog;
import com.kbplus.demo.log.model.query.LogQuery;
import com.kbplus.demo.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/allPage")
    @SystemLog(type = LogType.SELECT,description = "分页条件查询日志")
    public Page<MyLog> allPage(LogQuery logQuery, PageRequest pageRequest) {

        return logService.getAllPage(logQuery,pageRequest);
    }

    @GetMapping("/getLogType")
    public List<String> getLogType() {
        LogType[] values = LogType.values();
        ArrayList<String> objects = new ArrayList<>();
        for (LogType value : values) {
            objects.add(value.getName());
        }
        return objects;
    }

}
