package com.lexiang.utils.dataStructure.Tree;

public class Base {

    public static void main(String[] args) {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        BinaryNode<String> root = binaryTree.insert("A");

        BinaryNode<String> b = binaryTree.insert("B", root, true);
        BinaryNode<String> d = binaryTree.insert("D", b, true);
        BinaryNode<String> g = binaryTree.insert("G", d, false);

        BinaryNode<String> c = binaryTree.insert("C", root, false);
        BinaryNode<String> e = binaryTree.insert("E", c, true);
        BinaryNode<String> f = binaryTree.insert("F", c, false);
        binaryTree.insert("H",f,true);

        binaryTree.order(BinaryTree.IN_ORDER);



    }
}
