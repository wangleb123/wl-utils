package com.lexiang.utils.dataStructure.Tree;

public class BinaryTree<T> {

    public static final int BEFORE_ORDER = 1;
    public static final int IN_ORDER = 2;
    public static final int AFTER_ORDER = 3;

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryNode<T> insert(T data){
        this.root = new BinaryNode<T>(data,this.root,null);
        return this.root;
    }

    public BinaryNode<T> insert(T data,BinaryNode<T> parent,boolean isLeft){
        BinaryNode<T> child = null;
        if(data == null){
            System.out.println("插入的结点数据为空");
        }else {
            if(isLeft){
                if(parent.left == null){
                    child = new BinaryNode<>(data, null, null);
                    parent.left = child;

                }else {
                    throw new RuntimeException("该paren结点左孩子已经存在");
                }
            }else {
                if(parent.right == null){
                    child = new BinaryNode<>(data, null, null);
                    parent.right = child;
                }else {
                    throw new RuntimeException("该paren结点右孩子已经存在");
                }
            }
        }
        return child;
    }

    public void remove(BinaryNode<T> parent,boolean isLeft){
        if(isLeft){
            parent.left = null;
        }else {
            parent.right = null;
        }
    }


    /**
     * 遍历二叉树
     */
    public void order(){
        order(BinaryTree.BEFORE_ORDER);
    }

    public void order(Integer orderType){
        switch (orderType){
            case BEFORE_ORDER: beforeOrder(this.root);break;
            case IN_ORDER: inOrder(this.root);break;
            case AFTER_ORDER: afterOrder(this.root);break;
        }
    }


    /**
     * 先根次序
     * @param o
     */
    private void beforeOrder(BinaryNode<T> o){
        if(o != null){
            System.out.println(o.toString());
            beforeOrder(o.left);
            beforeOrder(o.right);
        }
    }


    /**
     * 中根次序
     * @param o
     */
    private void inOrder(BinaryNode<T> o){
        if(o != null){
            inOrder(o.left);
            System.out.println(o.toString());
            inOrder(o.right);
        }
    }


    /**
     * 后跟次序
     * @param o
     */
    private void afterOrder(BinaryNode<T> o){
        if(o != null){
            afterOrder(o.left);
            afterOrder(o.right);
            System.out.println(o.toString());
        }
    }
}
