package com.kbplus.demo.log.service.impl;

import com.kbplus.demo.common.entity.Page;
import com.kbplus.demo.common.entity.PageRequest;
import com.kbplus.demo.log.dao.LogDao;
import com.kbplus.demo.log.model.entity.MyLog;
import com.kbplus.demo.log.model.query.LogQuery;
import com.kbplus.demo.log.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao dao;

    @Override
    public void save(MyLog myLog) {
        myLog.setRecordTime(new Date());
        dao.save(myLog);
    }

    @Override
    public Page<MyLog> getAllPage(LogQuery logQuery, PageRequest pageRequest){


        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage()-1,pageRequest.getSize(), Sort.by(Sort.Direction.DESC,"recordTime"));

        BoolQueryBuilder queryBuilder= QueryBuilders.boolQuery();

        MatchQueryBuilder matchQueryBuilder1;
        MatchQueryBuilder matchQueryBuilder2;

        if(StringUtils.isNotEmpty(logQuery.getModuleName())) {
            matchQueryBuilder1 = new MatchQueryBuilder("moduleName", logQuery.getModuleName());
            queryBuilder.must(matchQueryBuilder1);
        }
        if(StringUtils.isNotEmpty(logQuery.getType())) {
            matchQueryBuilder2 = new MatchQueryBuilder("type", logQuery.getType());
            queryBuilder.must(matchQueryBuilder2);
        }

        org.springframework.data.domain.Page<MyLog> search = dao.search(queryBuilder,pageable);
        Page<MyLog> page = new Page<>(pageRequest.getPage(),pageRequest.getSize());
        page.setTotal(search.getTotalElements());
        page.setCurrent(search.getNumber()+1);
        page.setRecords(search.getContent());

        return page;
    }

}
