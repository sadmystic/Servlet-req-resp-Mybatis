package com.biu.web.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/*

响应字节数据:字节数据响应体
* */
@WebServlet("/resp4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 读取文件
        FileInputStream fls = new FileInputStream("C:\\Users\\BIU\\Desktop\\图\\微信图片_202203271522503.jpg");
        
        //2. 获取response字节输出流
        ServletOutputStream os = resp.getOutputStream();

        //3. 完成流的copy
/*        byte[] buff=new byte[1024];
        int len=0;
        while((len=fls.read(buff))!=-1){
            os.write(buff,0,len);
        }*/

        IOUtils.copy(fls,os);


        fls.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
