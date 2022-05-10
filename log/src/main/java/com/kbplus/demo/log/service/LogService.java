package com.kbplus.demo.log.service;

import com.kbplus.demo.common.entity.Page;
import com.kbplus.demo.common.entity.PageRequest;
import com.kbplus.demo.log.model.entity.MyLog;
import com.kbplus.demo.log.model.query.LogQuery;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
public interface LogService {

    void save(MyLog myLog);

    Page<MyLog> getAllPage(LogQuery logQuery, PageRequest pageRequest);
}
