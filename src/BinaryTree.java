public class BinaryTree {

    Node root;

    private Node addRecursive(Node current, int value) {
        // kezdés
        if(current == null) {
            return new Node(value);
        }

        if(value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if(value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            //already exists
            return current;
        }
        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if(current == null) {
            return false;
        }
        // in case the searched element is the root
        if(value == current.value) {
            return true;
        }
        // if not it goes on to check in the left and right side
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if(current == null) {
            return null;
        }
        if(value < current.value) {
           current.left = deleteRecursive(current.left, value);
        }
        else if(value > current.value) {
            current.right = deleteRecursive(current.right, value);
        }
        // deleting goes here
     else {
            if (current.left == null)
                return current.right;
            else if (current.right == null)
                return current.left;
            // node with two children
            // successor (smallest in the right subtree)
            current.value = findSmallestValue(current.right);

            // Delete the inorder successor
            current.right = deleteRecursive(current.right, current.value);
        }
       return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void delete(int value) {
        deleteRecursive(root, value);
    }

    public int countNodes(Node root) {
        if(root == null) {
            return 0;
        }
        return (1 + countNodes(root.right) + countNodes(root.left));
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }
}
