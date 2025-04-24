public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();
        tree.insert(50);
        tree.insert(60);
        tree.insert(30);
        tree.insert(80);
        tree.insert(10);
        tree.insert(40);
        tree.insert(35);
        tree.insert(45);
        tree.insert(36);
        tree.printInOrder();
        tree.remove(30);
        tree.printInOrder();
        tree.printBFS();
    }
}