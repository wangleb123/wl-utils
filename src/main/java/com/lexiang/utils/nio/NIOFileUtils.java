package com.lexiang.utils.nio;

import com.lexiang.utils.nio.file.FileCreateType;
import com.lexiang.utils.utils.WLStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

@Slf4j
public class NIOFileUtils {


    public static String  pathHand(String path,String fileName,Integer createType){
        if(WLStringUtils.isEmptyTrim(path)){
            log.error("请传入文件夹路径和");
            throw new RuntimeException("请传入文件夹路径");
        }
        if(WLStringUtils.isEmpty(fileName)){
            log.error("请传入文件名");
            throw new RuntimeException("请传入文件名");
        }
        if(null == createType){
            log.error("请传入创建文件方式");
            throw new RuntimeException("请传入创建文件方式");
        }

        if(!path.endsWith(File.separator)){
            path = path +File.separator;
            return path;
        }
        return path;
    }


    public static void main(String[] args) throws Exception{
        System.out.println(5 % 3);
        NIOFile
            .init()
            .capacity(1)
            .fileDic("/Users/wangle/Desktop/学习/boot-starter/wl-utils/src/main/java/com/lexiang/utils/")
            .fileName("2.txt")
            .createType(FileCreateType.ADD_DATA)
            .createFileOrDic("我是个java开发红参数1⃣️2⃣️3⃣️");

    }

}
