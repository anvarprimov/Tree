import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Node<T>{
    T item;
    Node<T> left;
    Node<T> right;
    public Node(T e){
        item = e;
    }

    boolean hasLeft() {
        return left != null;
    }
    boolean hasRight() {
        return right != null;
    }
}
public class MyTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T e) {
        if (root == null)
            root = new Node<>(e);
        else
            insertRec(root, e);
    }

    private void insertRec(Node<T> current, T e) {
        if (e.compareTo(current.item) < 0)
            if (current.hasLeft())
                insertRec(current.left, e);
            else
                current.left = new Node<>(e);
        else if (current.hasRight())
            insertRec(current.right, e);
        else
            current.right = new Node<>(e);
    }

    public void remove(T e) {
        Node<T> parent = null;
        Node<T> current = root;
        boolean right = false;
        while (current != null) {
            if (e.compareTo(current.item) < 0) {
                parent = current;
                current = current.left;
                right = false;
            } else if (e.compareTo(current.item) > 0) {
                parent = current;
                current = current.right;
                right = true;
            } else if (e.compareTo(current.item) == 0) {
                if (!current.hasRight()){
                    if (parent == null)
                        root = current.left;
                    else if (right)
                        parent.right = current.left;
                    else
                        parent.left = current.left;
                } else if (!current.right.hasLeft()){
                    if (parent == null) {
                        current.right.left = current.left;
                        root = current.right;
                    } else if (right) {
                        current.right.left = current.left;
                        parent.right = current.right;
                    }
                    else {
                        current.right.left = current.left;
                        parent.left = current.right;
                    }
                } else {
                    Node<T> lmParent = current.right;
                    Node<T> leftMost = current.right.left;
                    while (leftMost.hasLeft()){
                        lmParent = leftMost;
                        leftMost = leftMost.left;
                    }
                    lmParent.left = leftMost.right;
                    current.item = leftMost.item;
                }
                return;
            }
        }
    }

    public void printInOrder() {
        System.out.print("[");
        printInOrderRec(root);
        System.out.print("]");
        System.out.println();
    }

    private void printInOrderRec(Node<T> current) {
        if (current == null)
            return;
        printInOrderRec(current.left);
        System.out.print(current.item + " ");
        printInOrderRec(current.right);
    }

    public void printBFS(){
        Queue<Node<T>> queue = new ArrayDeque<>();
        ArrayList<T> list = new ArrayList<>();
        if (root != null) {
            queue.add(root);
            printBFSRec(queue, list);
        }
        System.out.println(list);
    }

    private void printBFSRec(Queue<Node<T>> queue, ArrayList<T> list) {
        if (queue.isEmpty())
            return;
        Node<T> current = queue.poll();
        list.add(current.item);
        if (current.hasLeft())
            queue.add(current.left);
        if (current.hasRight())
            queue.add(current.right);
        printBFSRec(queue, list);
    }
}