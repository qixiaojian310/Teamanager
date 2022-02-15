package com.jian.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisBuildUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
//        先新建一个mybatis的sqlSessionFactory实例来创建sqlSession
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession(){
        //相当于jdbc的connection对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

}
