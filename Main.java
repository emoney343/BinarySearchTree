public class Main {
    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(new FileEntry("projects", "folder", 0));
        tree.insert(new FileEntry("music", "folder", 0));
        tree.insert(new FileEntry("videos", "folder", 0));
        tree.insert(new FileEntry("beats", "file", 24));

        System.out.println("Files in alphabetical order:");
        tree.inOrder(tree.root);
    }
}