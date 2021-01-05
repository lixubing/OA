package com.edu.oa.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 框架中获取Bean
 * 获取ApplicationContext实例，根据此实例可以得到spring框架中管理的Bean
 */
@Component
public class FrameworkContext implements ApplicationContextAware {
//    @Resource
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取应用的applicationContext实例
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
