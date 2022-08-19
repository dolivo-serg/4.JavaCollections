package com.javarush.task.task36.task3604;


import com.javarush.task.task36.task3604.RedBlackTree.Node;

import java.io.PrintStream;

import static com.javarush.task.task36.task3604.BinaryTreePrinter.Appearance.*;
import static com.javarush.task.task36.task3604.RedBlackTree.Color.RED;


public class BinaryTreePrinter {
        public enum Appearance {
            FONT, BACKGROUND, PATH
        }

        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
        private static final String ANSI_BRIGHT_RED = "\u001B[91m";
        private static final String ANSI_BRIGHT_BG_WHITE = "\u001B[107m";
        private static final String ANSI_BG_WHITE = "\u001B[47m";

        private static final String RIGHT = "└──";
        private static final String RIGHT_DOWN = "├──";
        private static final String DOWN = "│  ";
        private static final String HALL = "   ";

        private final Node tree;

        public BinaryTreePrinter(Node tree) {
            this.tree = tree;
        }

    private String traversePreOrder(Node root) {
        final StringBuilder builder = new StringBuilder();

        if (!root.isEmpty()) {
            builder.append(root.getValue());

            traverseNodes(builder, "", appearance(root, PATH), root.getLeft(), hasRight(root));
            traverseNodes(builder, "", RIGHT, root.getRight(), false);
        }
        return builder.toString();
    }

    private void traverseNodes(StringBuilder builder, String padding, String path, Node node,
                               boolean hasRightSibling) {
        if (!node.isEmpty()) {
            builder.append("\n")
                    .append(padding)
                    .append(path)
                    .append(appearance(node, FONT))
                    .append(appearance(node, BACKGROUND))
                    .append(node.getValue())
                    .append(ANSI_RESET);
            padding += hasRightSibling ? DOWN : HALL;
            traverseNodes(builder, padding, appearance(node, PATH), node.getLeft(), hasRight(node));
            traverseNodes(builder, padding, RIGHT, node.getRight(), false);
        }
    }
    private String appearance(Node node, Appearance appearance) {
        switch (appearance) {
            case FONT:
                return node.getColor() == RED ? ANSI_BRIGHT_RED : ANSI_BRIGHT_BLACK;
            case BACKGROUND:
                return node.getColor() == RED ? ANSI_BG_WHITE : ANSI_BRIGHT_BG_WHITE;
            case PATH:
                return  !node.getRight().isEmpty() ? RIGHT_DOWN : RIGHT;
            default:
                throw new AppearanceException("" + appearance);
        }
    }

    private boolean hasRight(Node node) {
        return !node.getRight().isEmpty();
    }

    public void print(PrintStream outStream) {
        outStream.println(traversePreOrder(tree));
    }
    public static class AppearanceException extends RuntimeException {

        public AppearanceException(String appearance) {
            super("Inappropriate appearance value: " + appearance);
        }
    }
}
