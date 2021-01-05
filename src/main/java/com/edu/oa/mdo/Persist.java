package com.edu.oa.mdo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class Persist {
    @Resource
    private SqlSessionTemplate sessionTemplate;
}
