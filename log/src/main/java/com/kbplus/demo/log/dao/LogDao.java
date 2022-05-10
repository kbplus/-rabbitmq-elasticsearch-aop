package com.kbplus.demo.log.dao;

import com.kbplus.demo.log.model.entity.MyLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Repository
public interface LogDao extends ElasticsearchRepository<MyLog, String> {

}
