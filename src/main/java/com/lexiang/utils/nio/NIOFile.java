package com.lexiang.utils.nio;
import com.lexiang.utils.exception.BusinessException;
import com.lexiang.utils.utils.WLObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;


@Slf4j
@Data
@AllArgsConstructor
public class NIOFile {


    /**
     * 文件夹路径
     */
    private String path;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * isCreate
     */
    private boolean isRecover;


    public  void createFileOrDic(){
        this.path = NIOFileUtils.pathHand(this.path,fileName);
        String fullPath = this.path+this.fileName;
        try {
            if(Files.exists(Paths.get(this.path))){
                if(!Files.exists(Paths.get(fullPath)) || this.isRecover){
                   Files.createFile(Paths.get(fullPath));
                }
            }else {
                Files.createDirectories(Paths.get(this.path));
                Files.createFile(Paths.get(fullPath));
            }
        }catch (IOException e){
            log.error("创建文件失败");
            throw new BusinessException("创建文件失败"+e.getMessage());
        }
    }

    public void writeData(Object data){

        this.path = NIOFileUtils.pathHand(this.path,fileName);
        String fullName = this.path + fileName;
        RandomAccessFile fromFile = null;
        FileChannel channel = null;
        //获取nio文件对象
        try {
            fromFile = new RandomAccessFile(fullName, "rw");
            //获取文件对象的channel
            channel = fromFile.getChannel();
            //新建缓冲区
            ByteBuffer bf = ByteBuffer.allocate(1024);
            //获取缓冲区的大小
            int capacity = bf.capacity();
            byte[] bytes = data.toString().getBytes();
            int extra = bytes.length % capacity;
            int nums = bytes.length / capacity;
            for (int i = 0; i <=nums; i++) {
                //将额外填充（1.当已经填充完了num个capacity时 2.连第一个capacity都无法填满时）
                if(i == nums || nums == 0){
                    bf.put(bytes,nums * capacity,extra);
                }else {
                    bf.put(bytes,(i) * capacity,bf.capacity());
                }
                //切换到读模式 position设置为0 limit设置为之前的position
                bf.flip();
                channel.write(bf);
                //情况缓冲区
                bf.clear();
            }
        }catch (IOException e){
            log.error("数据写入"+fullName+"出错！");
            e.printStackTrace();
        }finally {
            try {
                if (!WLObjectUtils.isEmpty(channel)) {
                    channel.close();
                }
                if (!WLObjectUtils.isEmpty(fromFile)) {
                    fromFile.close();
                }
            }catch (Exception e){
                log.error("文件通道关闭失败");
            }
        }





//            int readNumber = readChannel.read(buff);
//            if (readNumber == -1) {
//                break;
//            }
//            // 5. 传出数据准备：调用flip()方法
//            buff.flip();
//
//            // 6. 从 Buffer 中读取数据 & 传出数据到通道
//            outChannel.write(buff);
//
//            // 7. 重置缓冲区
//            buff.clear();
        }


    public void  copy(String path,String fileName,boolean isRecover){


    }
    public static void main(String[] args) throws Exception{
        System.out.println(5 % 3);
        NIOFile nioFile = new NIOFile(null,"2.txt",false);
        nioFile.createFileOrDic();
        nioFile.writeData("/Users/wangle/Desktop/java/maven/apache-maven-3.6.2/");
    }

}
