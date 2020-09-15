package com.lexiang.utils.dataStructure.linerlist;

public class Base {

    public static void main(String[] args) throws Exception{
        SeqList<String> linearList = new SeqList<>();
        for (int i = 0; i < 20; i++) {
            linearList.insert(Integer.toString(i));
        }
        linearList.insert("22");
        //linearList.remove(0);
        //linearList.clear();

        //linearList.remove(3);
        linearList.isEmpty();
        System.out.println(linearList.toString());
        SeqList<String> linearList1 = new SeqList<>();
        System.out.println(linearList1.toString());


        String o = "123";
        String o1 = "123";
        System.out.println(o == o1);

    }
}
