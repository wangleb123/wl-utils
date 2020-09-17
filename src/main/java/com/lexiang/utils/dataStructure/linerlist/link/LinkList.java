package com.lexiang.utils.dataStructure.linerlist.link;

public class LinkList<T> {

    private Node<T> head;


    //空链表就是head= null
    public LinkList(){
        head = null;
    }


    public void insert(Node<T> node){

        //空表插入，让head指向插入的结点
        if(head == null){
            head = node;
        }else {
            node.next = head;
            head = node;
        }
    }




    @Override
    public String toString() {
        return "LinkList{" +
                "head=" + head +
                '}';
    }
}
