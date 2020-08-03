package com.lexiang.wlutils.poi;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.lexiang.wlutils.web.RequestUtils;
import lombok.NoArgsConstructor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
/**
 * 导出excel工具（最简单的样式，支持数据处理，实现DownloadExcelAdaptor即可）
 */
public class DownloadExcelUtils {

    /**
     * excel处理结束后的rows
     */
    private ArrayList<Map<String, Object>> date;

    /**
     * excel列名
     */
    private Map<String,String> titileMap;

    /**
     * 需要导出的数据集合
     */
    private List list;

    /**
     * 输出占位的空行，读取的时候有可能是按照行读取，防止行数读取错误
     */
    private Integer extentRow = 0;

    /**
     * 导出的excel文件名
     */
    private String fileName;


    /**
     *
     * @param titileMap excel列名
     * @param list 需要导出的数据集合
     * @param fileName 导出的excel文件名
     * @return DownloadExcelUtis对象
     */
    public DownloadExcelUtils init(Map<String,String> titileMap, List list, String fileName){
        this.titileMap = titileMap;
        this.fileName = fileName;
        this.list = list;
        return this;
    }

    public DownloadExcelUtils extendRow(Integer extentRow){
        this.extentRow = extentRow;
        return this;
    }

    /**
     *
     * @param downloadExcelAdaptor excel数据适配器{传入null值则默认数据处理}
     * @return DownloadExcelUtis对象
     */
    public DownloadExcelUtils handler(DownloadExcelAdaptor downloadExcelAdaptor){
        if(list == null|| list.size() == 0){
            throw new RuntimeException("导出数据为空!");
        }else {
            ArrayList<Map<String, Object>> rows = new ArrayList<>();
            for (Object employeeFile : list) {
                Map<String, Object> tm = new LinkedHashMap<>();
                for (String s : titileMap.keySet()) {
                    tm.put(titileMap.get(s), DownloadExcelUtils.getProperties(employeeFile,s));
                }
                rows.add(tm);
            }
            //不需要自定义处理
            if(downloadExcelAdaptor == null){
                this.date = rows;
            }else {
                this.date = downloadExcelAdaptor.data(titileMap,rows);
            }
        }
        return this;
    }


    /**
     * 输出excel
     */
    public void write(){
        HttpServletResponse response = RequestUtils.getResponse();
        ServletOutputStream responseStream = null;
        ExcelWriter writer = ExcelUtil.getWriter();
        try {
            responseStream = response.getOutputStream();
            ArrayList<Map<String, Object>> date = this.date;

            // 通过工具类创建writer，默认创建xls格式

            for (int i = 0;i<extentRow;i++){
                writer.merge(titileMap.size()-1, "");
            }

            int i = 0;
            for (String key: titileMap.keySet()) {
                writer.setColumnWidth(i,20);
                i++;
            }
            // 一次性写出内容，使用默认样式
            writer.write(date);
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="+new String(this.fileName.getBytes(),"iso-8859-1")+".xls" );// 设定输出文件头
            response.setContentType("application/x-download");

            // 关闭writer，释放内存
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("导出"+fileName+"出错");

        }finally {
            try {
                writer.flush(responseStream);
                responseStream.close();
                writer.close();
            }catch (Exception e){

            }
        }
    }

    /**
     * 反射动态获取属性值
     * @param object
     * @param methedName
     * @return
     */
    public static Object getProperties(Object object,String methedName){
        methedName = "get"+methedName.substring(0,1).toUpperCase()+ methedName.substring(1,methedName.length());
        try {
            Method declaredMethod = object.getClass().getDeclaredMethod(methedName);
            Object invoke = declaredMethod.invoke(object);
            return invoke;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("导出数据名称"+methedName+"没对上，请检查代码!");
        }
    }


    public void test(){
        System.out.println("   //导出excel\n" +
                "        new DownloadExcelUtis()\n" +
                "                .init(titleMap, list, \"考勤报表\")\n" +
                "                .handler(null)\n" +
                "                .write();");
    }

}
