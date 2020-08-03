package com.lexiang.wlutils.token;

import com.lexiang.wlutils.web.RequestUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 王乐
 * @apiNote  token工具类
 */
@Slf4j
public class TokenUtils {

    /**
     * <>判断header中是否有token信息</>
      * @return
     */
   public static boolean isExit(){
        HttpServletRequest request = RequestUtils.getRequest();
        String token;

       if(request.getHeader("x-token") == null){
           token =  request.getParameter("x-token");
       }else {
           token = request.getHeader("x-token");
       }
        if(null == token || "".equals(token) ){
            log.info("访问地址为>>>>>"+request.getRequestURI());
            log.info("访问IP为>>>>>"+request.getRemoteAddr());
            return false;
        }else {
            return true;
        }
    }


    /**
     * <>获取header中的token</>
     * @return header中的token
     */
    public static String getToken(){
        HttpServletRequest request = RequestUtils.getRequest();
        if(request.getHeader("x-token") == null){
            return request.getParameter("x-token");
        }else {
            return request.getHeader("x-token");
        }
    }
}
