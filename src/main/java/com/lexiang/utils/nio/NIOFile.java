package com.lexiang.utils.nio;

import com.lexiang.utils.nio.file.FileCreateType;
import com.lexiang.utils.utils.WLObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private int createType = FileCreateType.ADD_DATA;

    private int capacity = 1024;


    public static NIOFile init() {
        return new NIOFile();
    }


    public NIOFile capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public NIOFile fileDic(String path) {
        this.path = path;
        return this;
    }

    public NIOFile fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public NIOFile createType(int createType) {
        this.createType = createType;
        return this;
    }



    public void date(Object data) throws Exception {
        this.path = NIOFileUtils.pathHand(this.path, this.fileName, this.createType);
        String fullPath = this.path + this.fileName;
        if (Files.exists(Paths.get(this.path))) {
            createType(data, fullPath);
        } else {
            Files.createDirectories(Paths.get(this.path));
            Files.createFile(Paths.get(fullPath));
            writeData(data);
        }
    }


    private void writeData(Object data) {
        this.path = NIOFileUtils.pathHand(this.path, this.fileName, this.createType);
        String fullName = this.path + this.fileName;
        RandomAccessFile fromFile = null;
        FileChannel channel = null;

        //获取nio文件对象
        try {
            fromFile = new RandomAccessFile(fullName, "rw");
            channel = fromFile.getChannel();
            byte[] dataBytes = null;
            if (data instanceof String) {
                dataBytes = data.toString().getBytes();
            }
            if (data instanceof MultipartFile) {
                byte[] bytes = ((MultipartFile) data).getBytes();
                channel = fromFile.getChannel();
                dataBytes = bytes;
            }

            if(!WLObjectUtils.isEmpty(dataBytes)){
                Handler(dataBytes, channel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!WLObjectUtils.isEmpty(channel)) {
                    channel.close();
                }
                if (!WLObjectUtils.isEmpty(fromFile)) {
                    fromFile.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createType(Object data, String fullPath) throws Exception {
        if (!Files.exists(Paths.get(fullPath))) {
            Files.createFile(Paths.get(fullPath));
        }
        switch (this.createType) {
            case FileCreateType.ADD_DATA:
                writeData(data);
                break;
            case FileCreateType.RECOVER:
                if (!Files.exists(Paths.get(fullPath))) {
                    Files.delete(Paths.get(fullPath));
                }
                Files.createFile(Paths.get(fullPath));
                writeData(data);
                break;
        }
    }



    private void Handler(byte[] bytes, FileChannel channel) throws Exception {

        ByteBuffer bf = ByteBuffer.allocate(this.capacity);
        long size = channel.size();
        channel.position(size);
        int extra = bytes.length % this.capacity;
        int nums = bytes.length / this.capacity;
        for (int i = 0; i <= nums; i++) {
            //将额外填充（1.当已经填充完了num个capacity时 2.连第一个capacity都无法填满时）
            if (i == nums || nums == 0) {
                int offset = nums * this.capacity;
                bf.put(bytes, offset, extra);
            } else {
                int offset = (i) * this.capacity;
                bf.put(bytes, offset, bf.capacity());
            }
            //切换到读模式 position设置为0 limit设置为之前的position
            bf.flip();
            channel.write(bf);
            //情况缓冲区
            bf.clear();
        }

    }

    public void copy(String path, String fileName, boolean isRecover) {


    }


}
