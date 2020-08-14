package com.lexiang.utils.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author 王乐
 * @apiNote  请求工具类
 */
public class RequestUtils {

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        return  request;
    }

    public static HttpServletResponse getResponse(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        return  response;
    }









    public static void write(String text, String charSetName, String contentType) throws IOException {
        HttpServletResponse resp = RequestUtils.getResponse();
        if (resp != null) {
            resp.setCharacterEncoding(charSetName);
            resp.setContentType(contentType);
            PrintWriter out = resp.getWriter();
            out.print(text);
            out.flush();
            out.close();
        }
    }

}
