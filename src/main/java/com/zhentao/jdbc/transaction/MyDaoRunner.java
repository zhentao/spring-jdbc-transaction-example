package com.zhentao.jdbc.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyDaoRunner {
    @Autowired
    private MyDao myDao;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        MyDaoRunner runner = context.getBean(MyDaoRunner.class);
        runner.run();

    }

    public void run() {
        myDao.insert("joe");
        System.out.println("success");
    }
}
