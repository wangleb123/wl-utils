package com.lexiang.utils.dataStructure.linerlist;
import java.util.*;


/**
 * 线性表的顺序存储结构实现
 * @param <T>
 */
public class SeqList<T>{


    /**
     * 存放线性表元素的数组
     */
    transient Object[] elementData;

    /**
     * 线性表默认默认空间大小
     */
    private static final int DEFAULT_SIZE = 10;


    /**
     * 超出线性表空间扩展内存大小
     */
    private static final int EXTEND_SIZE = 10;


    /**
     * 线性表数据长度
     */
    private int length;

    /**
     * 线性表内存大小
     */
    private int size;


    public SeqList(){
        this(DEFAULT_SIZE);
    }

    public SeqList(int initialSIze){
        if(initialSIze >= 0 ){
            this.elementData = new Object[initialSIze];
        }else {
            throw new IllegalArgumentException("线性表长度不能小于0");
        }
        this.length = 0;
        this.size = initialSIze;
    }

    /**
     * @return 线性表数据长度
     */
    public int dataSize() {
        return this.length;
    }

    /**
     *
     * @return 线性表内存大小
     */
    public int size(){
        return this.size;
    }

    /**
     * @return 线性表是否为空表
     */
    public boolean isEmpty() {
        return this.length == 0;
    }


    /**
     *
     * @param o 数据
     * @return 线性表中是否存在该数据
     */
    public boolean contains(Object o) {
        return indexOf(o).size() != 0;
    }

    /**
     * 添加数据
     * @param o 数据
     */
    public void add(Object o) {
        extendElement();
        this.elementData[this.length++] = o;
    }

    /**
     * 清空线性表
     */
    public void clear() {
        this.elementData = new Object[DEFAULT_SIZE];
        this.length = 0;
    }

    /**
     * 获取指定下标的数据
     * @param index 下标
     * @return 下标对应的数据
     */
    public T get(int index) {
        if(this.length ==0 ){
            return null;
            //throw new ArrayIndexOutOfBoundsException("线性表已空");
        }else if(this.length-1 < index){
            return null;
            //throw new ArrayIndexOutOfBoundsException("线性表["+index+"]位置已没有元素");
        }else {
            return (T)elementData[index];
        }
    }

    /**
     * 移除元素
     * @param index 地址下标
     * @return
     */
    public T remove(int index) {

        if(index > this.length -1){
            return null;
        }else {
            Object data = this.elementData[index];
            for (int i = index; i < this.length-1; i++) {
                this.elementData[i] = this.elementData[i+1];
            }
            // 将index之后的数据的地址引用向前移动一个
            // 如果线性表表满，最后一个元素的地址引用赋值给前一个
            // 那么最后一个元素仍然持有原来线性表的最后一个元素的地址引用
            // 会导致数据重复
            // 这里就将最后一个元素的地址引用置空
            this.elementData[this.length-1] = null;
            this.length -- ;
            return (T)data;
        }

    }

    public void insert(int index,Object o){
        extendElement();
        if(index > this.length -1 ){
            this.elementData[length++] = o;
        }else {
            for (int i = index; i < this.length; i++) {
                this.elementData[i+1] = this.elementData[i];
            }
            this.elementData[index] = o;
        }
        this.length++;

    }



    public ArrayList<Integer> indexOf(Object o){
        ArrayList<Integer> indexOf = new ArrayList<>();
        if(o == null){
            for (int i = 0; i < this.length; i++) {
                if (this.elementData[i] == null) {
                    indexOf.add(i);
                }

            }
        }else {
            for (int i = 0; i < this.length; i++) {
                if (this.elementData[i].equals(o)) {
                    indexOf.add(i);
                }
            }
        }
        return indexOf;
    }


    /**
     * 线性表已经超出，默认添加EXTEND_SIZE个内存空间
     */
    private void extendElement(){
        if(this.length == this.size){
            this.size =  this.size + EXTEND_SIZE;
            Object[] extend = new Object[this.size];
            for (int i = 0; i < this.length; i++) {
                extend[i] = this.elementData[i];
            }
            this.elementData = extend;
        }
    }

    public String toString() {
        return "SeqList{" +
                "数据=" + Arrays.toString(this.elementData) +"\n"+
                "内存大小"+ this.size+"\n"+
                "数据量"+ this.length+"\n"+
                '}';
    }


    public static void main(String[] args) throws Exception{
        SeqList<String> linearList = new SeqList<>();
        for (int i = 0; i < 20; i++) {
            linearList.add(Integer.toString(i));
        }
        linearList.add("22");
        //linearList.remove(0);
        //linearList.clear();

        //linearList.remove(3);
        linearList.isEmpty();
        System.out.println(linearList.toString());

        SeqList<String> linearList1 = new SeqList<>();
        System.out.println(linearList1.toString());

    }
}
