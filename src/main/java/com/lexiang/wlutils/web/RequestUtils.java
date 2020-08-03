package com.lexiang.wlutils.web;

import com.alibaba.fastjson.JSON;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 王乐
 * @apiNote  获取request与response
 */
public class RequestUtils {


    /**
     * 获取request对象
     * @return request
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        return  request;
    }


    /**
     * 获取response对象
     * @return response
     */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        return  response;
    }


    /**
     * 将数据写到response后输出
     * @param obj 数据对象
     * @throws IOException
     */
    public static void writeJson(Object obj) throws IOException {
        if (obj instanceof String) {
            write((String)obj, "utf-8", "application/json");
        } else {
            write(JSON.toJSONString(obj), "utf-8", "application/json");
        }
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
