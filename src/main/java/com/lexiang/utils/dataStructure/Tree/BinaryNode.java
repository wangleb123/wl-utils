package com.lexiang.utils.dataStructure.Tree;

public class BinaryNode<T> {

    public T data;

    public BinaryNode<T> left;

    public BinaryNode<T> right;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data){
        this(data ,null ,null);
    }

    /**
     * @return 是否为椰子树结点
     */
    public boolean leaf(){
        return this.left == null && this.right == null;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
