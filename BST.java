public class BST {
    Node root;

    void insert(FileEntry file) {
        root = insertRecursive(root, file);
    }

    Node insertRecursive(Node current, FileEntry file) {
        if (current == null) {
            return new Node(file);
        }

        int cmp = file.name.compareTo(current.data.name);

        if (cmp < 0) {
            current.left = insertRecursive(current.left, file);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, file);
        }

        return current;

    }

    void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data.name + " | " + node.data.type + " | " + node.data.size + "kb");
        inOrder(node.right);
    }
}
