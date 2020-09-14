package com.lexiang.utils.dataStructure.linerlist;

import org.apache.commons.lang3.StringUtils;

import javax.jws.Oneway;
import java.util.*;

public class SeqList<T>{


    /**
     * 存放线性表元素的数组
     */
    transient Object[] elementData;

    /**
     * 线性表默认默认空间大小
     */
    private static final Integer DEFAULT_SIZE = 10;

    /**
     * 线性表目前插入所在位置
     */
    private static int position;


    /**
     * 线性表目前插入所在位置
     */
    private static int length;

    /**
     * 空线性表
     */
    private static final Object[] DEFAULT_DATA = {};


    public SeqList(){
        this(DEFAULT_SIZE);
    }

    public SeqList(int initialSIze){
        if(initialSIze >= 0 ){
            elementData = new Object[initialSIze];
        }else {
            throw new IllegalArgumentException("线性表长度不能小于0");
        }
        position = -1;
        length = initialSIze;
    }

    public int size() {
        return elementData.length;
    }

    public boolean isEmpty() {
        return position == 0;
    }


    public boolean contains(Object o) {
        return indexOf(o).size() != 0;
    }

    public boolean add(Object o) {
        elementData[++position] = o;
        return true;
    }

    public void clear() {
        elementData = null;
        position = -1;
        elementData = new Object[length];
    }

    public T get(int index) {
        if(elementData.length <=0 ){
            throw new ArrayIndexOutOfBoundsException("线性表已空");
        }else if(elementData.length-1 < index){
            throw new ArrayIndexOutOfBoundsException("线性表["+index+"]位置已没有元素");
        }
        return (T)elementData[index];
    }

    public T remove(int index) {
        Object data = elementData[index];
        for (int i = index; i < elementData.length-1; i++) {
            elementData[i] = elementData[i+1];
        }
        elementData[position] = null;
        position -- ;
        return (T)data;
    }

    public String toString() {
        return "SeqList{" +
                "elementData=" + Arrays.toString(elementData) +
                '}';
    }

    public ArrayList<Integer> indexOf(Object o){
        ArrayList<Integer> indexOf = new ArrayList<>();
        if(o == null){
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] == null) {
                    indexOf.add(i);
                }

            }
        }else {
            for (int i = 0; i < position+1; i++) {
                if (elementData[i].equals(o)) {
                    indexOf.add(i);
                }
            }
        }
        return indexOf;
    }

    public static void main(String[] args) throws Exception{
        SeqList<String> linearList = new SeqList<>();
        linearList.add("123");
        linearList.remove(0);
        linearList.clear();
        linearList.add("234");
        linearList.add("234");
        linearList.add("234");
        linearList.size();
        linearList.isEmpty();
        System.out.println(linearList.indexOf("234"));

    }
}
