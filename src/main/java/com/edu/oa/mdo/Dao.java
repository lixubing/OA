package com.edu.oa.mdo;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("dao")
public class Dao {
    @Resource
    private SqlSessionTemplate sessionTemplate;

    public Object getObjectByParam(String statement, Object parameter){
        Object o = sessionTemplate.selectOne(statement, parameter);
        return o;
    }
    public List getListByParam(String statement, Object parameter){
        List<Object> list = sessionTemplate.selectList(statement, parameter);
        return list;
    }
    public List getListByParam(String statement, Object parameter, RowBounds rowBounds){
        List<Object> list = sessionTemplate.selectList(statement, parameter, rowBounds);
        return list;
    }
    public int update(String statement, Object parameter){
        int update = sessionTemplate.update(statement, parameter);
        return update;
    }
    public int insert(String statement, Object parameter){
        int insert = sessionTemplate.insert(statement, parameter);
        return insert;
    }
    public int delete(String statement, Object parameter){
        int delete = sessionTemplate.delete(statement, parameter);
        return delete;
    }
}
