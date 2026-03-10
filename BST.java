public class BST {
    Node root;

    // inserts a new file into the tree
    void insert(FileEntry file) {
        root = insertRecursive(root, file);
    }

    // recursively finds the correct spot and places the file
    Node insertRecursive(Node current, FileEntry file) {
        // empty spot found, place the file here
        if (current == null) {
            return new Node(file);
        }

        // compare the new file name to the current node's name alphabetically
        int cmp = file.name.compareTo(current.data.name);

        if (cmp < 0) {
            // new file comes before current alphabetically, go left
            current.left = insertRecursive(current.left, file);
        } else if (cmp > 0) {
            // new file comes after current alphabetically, go right
            current.right = insertRecursive(current.right, file);
        }
        // cmp == 0 means duplicate name, ignore it

        return current;
    }

    // prints all files in alphabetical order (left -> current -> right)
    void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data.name + " | " + node.data.type + " | " + node.data.size + "kb");
        inOrder(node.right);
    }

    // kicks off the search from the root
    FileEntry search(String name) {
        return searchRecursive(root, name);
    }

    // recursively searches for a file by name, returns null if not found
    FileEntry searchRecursive(Node current, String name) {
        // reached an empty spot, file doesnt exist
        if (current == null) {
            return null;
        }

        // compare the search name to the current node's name
        int cmp = name.compareTo(current.data.name);

        if (cmp < 0) {
            // search name comes before current, go left
            return searchRecursive(current.left, name);
        } else if (cmp > 0) {
            // search name comes after current, go right
            return searchRecursive(current.right, name);
        } else {
            // cmp == 0, found the file
            return current.data;
        }
    }

    // kicks off the deletion from the root
    void delete(String name) {
        root = deleteRecursive(root, name);
    }

    // recursively finds and removes a file, handles 3 cases
    Node deleteRecursive(Node current, String name) {
        // file not found
        if (current == null) {
            System.out.println("File not found.");
            return null;
        }

        // compare the name to the current node's name
        int cmp = name.compareTo(current.data.name);

        if (cmp < 0) {
            // name comes before current, go left
            current.left = deleteRecursive(current.left, name);
        } else if (cmp > 0) {
            // name comes after current, go right
            current.right = deleteRecursive(current.right, name);
        } else {
            // found the node to delete

            // case 1 and 2 - no left child or no right child
            // replace the node with whichever child exists
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // case 3 - two children
            // find the in-order successor (smallest name in right subtree)
            // replace current data with successor, then delete the successor
            Node successor = findMin(current.right);
            current.data = successor.data;
            current.right = deleteRecursive(current.right, successor.data.name);
        }

        return current;
    }

    // finds the leftmost node which is always the smallest alphabetically
    Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    void update(String name, String newName, String newType, long newSize) {
        FileEntry old = search(name);
        if (old == null) {
            System.out.println("File not found.");
            return;
        }
        delete(name);
        insert(new FileEntry(newName, newType, newSize));
    }

    FileEntry getIndex(int index) {
        count[0] = 0;
        return getIndex(root, index);
    }

    
    int[] count = {0};
    FileEntry getIndex (Node node, int index) {
        if (node == null) return null;


        FileEntry left = getIndex(node.left, index);
        if (left != null) return left;

        count[0]++;
         if (count[0] - 1 == index) {
            return node.data;
        }

        FileEntry right = getIndex(node.right, index);
        if (right != null) return right;

        return null;
        
        
        

    }







}