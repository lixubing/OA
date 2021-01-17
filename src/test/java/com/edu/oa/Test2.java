package com.edu.oa;

import com.edu.oa.mdo.CourseDo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class Test2 {
    @Resource
    private SqlSessionTemplate sessionTemplate;
    @Resource
    private CourseDo mdo;
//	@Resource
//	private IProcessService processService;

    @Test
    public void test1(){
        System.out.println("1");
    }
}
