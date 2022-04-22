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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接受用户数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //封装用户对象
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        //2. 调用mapper 判断
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

        //2.4 方法
        User user1 = mapper.selectByUsername(username);

        //3. 判断是否为null
        if(user1==null){
            //不存在
            mapper.add(user);

            //提交事务
            sqlSession.commit();
            //释放资源
            sqlSession.close();
        }else {
            //存在
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("用户名已存在");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
