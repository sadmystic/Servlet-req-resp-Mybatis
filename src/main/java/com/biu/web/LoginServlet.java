package com.biu.web;

import com.biu.mapper.UserMapper;
import com.biu.pojo.User;
import com.biu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接受用户名密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2. 调用Mybatis
        //2.1 获取SqlSessionFactory对象
/*
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
*/

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.2 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.3 获取Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //2.4 调用方法
        User user = mapper.select(username, password);

        //2.5 释放资源
        sqlSession.close();

        //获取字符输出流，并设置content type
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        //3. 判断user是否为null
        if(user!=null){
            //登录成功
            writer.write("登录成功！");
        }else {
            //登录失败
            writer.write("登录失败！");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
