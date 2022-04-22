package com.biu.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
* 中文乱码解决方案
* */
@WebServlet("/req3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码:POST getReader()
        //req.setCharacterEncoding("UTF-8");


        String username = req.getParameter("username");
        System.out.println(username);

        // get解决方式
        //乱码原因：tomcat进行url解码时字符集是ISO-8859-1
/*        byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        username = new String(bytes, StandardCharsets.UTF_8);*/

        username=new String(username.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        System.out.println("解决乱码后:"+username);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
