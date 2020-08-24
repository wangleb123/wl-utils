package com.lexiang.utils.nio;

import com.lexiang.utils.exception.BusinessException;
import com.lexiang.utils.utils.WLStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

@Slf4j
public class NIOFileUtils {

    public static String  pathHand(String path,String fileName){
        if(WLStringUtils.isEmpty(path)){
            log.error("请传入文件夹路径和");
            throw new BusinessException("请传入文件夹路径");
        }
        if(WLStringUtils.isEmpty(fileName)){
            log.error("请传入文件名");
            throw new BusinessException("请传入文件名");
        }
        if(path.endsWith(File.separator)){
            path = path +File.separator;
            return path;
        }
        return null;
    }

}
