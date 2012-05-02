package com.zhentao.jdbc.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyDaoRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        MyDao dao = context.getBean(MyDao.class);
        dao.insert("joe");
        System.out.println("success");
    }
}
