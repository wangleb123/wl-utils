package com.lexiang.utils.dataStructure.linerlist.link;


import lombok.Data;

public class Node<T> {

    public Node<T> next;

    public T data;

    public Node(T data,Node<T> next) {
        this.next = next;
        this.data = data;
    }

    public Node() {
        this(null,null);
    }
}
