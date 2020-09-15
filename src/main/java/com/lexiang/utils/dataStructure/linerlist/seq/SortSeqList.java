package com.lexiang.utils.dataStructure.linerlist.seq;

public class SortSeqList<T extends Comparable<? super T>> extends SeqList<T> {

    public SortSeqList(){
        super();
    }

    public SortSeqList(int initialSize){
        super(initialSize);
    }


    public SortSeqList(T[] values){
        super(values.length);
        for (int i = 0; i < values.length; i++) {
            T value = values[i];
            this.insert(value);
        }
    }

    @Override
    public int insert(T o){
        //顺序表为空或者大于最后一个元素，直接添加到末尾
        if(isEmpty() || o.compareTo(get(dataSize()-1)) > 0){
            super.insert(o);
            return 0;
        }else {
            for (int i = 0; i < dataSize(); i++) {
                if(o.compareTo(get(i)) < 0){
                    super.insert(i,o);
                    return i;
                }

            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //ArrayList<Object> datas = new ArrayList<>(10000000);
        SortSeqList<Integer> tSortSeqList = new SortSeqList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            tSortSeqList.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end - start));
        System.out.println(tSortSeqList.toString());

    }

}
