import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private TreeNode root;

    public boolean insert(int value) {
        if (search(value)) {
            return false;
        }
        root = insert(root, value);
        return true;
    }

    public boolean delete(int value) {
        if (!search(value)) {
            return false;
        }
        root = delete(root, value);
        return true;
    }

    public boolean search(int value) {
        TreeNode current = root;
        while (current != null) {
            if (value == current.data) {
                return true;
            }
            current = value < current.data ? current.left : current.right;
        }
        return false;
    }

    public List<Integer> inOrder() {
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);
        return values;
    }

    public List<Integer> preOrder() {
        List<Integer> values = new ArrayList<>();
        preOrder(root, values);
        return values;
    }

    public List<Integer> postOrder() {
        List<Integer> values = new ArrayList<>();
        postOrder(root, values);
        return values;
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.data) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }

    private TreeNode delete(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.data) {
            node.left = delete(node.left, value);
        } else if (value > node.data) {
            node.right = delete(node.right, value);
        } else if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            TreeNode successor = minimum(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }

    private TreeNode minimum(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void inOrder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        inOrder(node.left, values);
        values.add(node.data);
        inOrder(node.right, values);
    }

    private void preOrder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        values.add(node.data);
        preOrder(node.left, values);
        preOrder(node.right, values);
    }

    private void postOrder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        postOrder(node.left, values);
        postOrder(node.right, values);
        values.add(node.data);
    }
}
