package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
*/

public class Solution {
    public static void main(String[] args) {
        final RedBlackTree tree = new RedBlackTree();

        for (int i = 0; i < 8; i++) {
            tree.insert(i);
        }
        new BinaryTreePrinter(tree.getHeader()).print(System.out);
    }
}
