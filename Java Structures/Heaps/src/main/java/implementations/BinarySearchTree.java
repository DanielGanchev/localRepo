package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;
    private Node<E> left;
    private Node<E> right;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> tree) {
        this.copy(tree);
    }

    private void copy(Node<E> root) {
        if (root != null){
            this.insert(root.value);
            this.copy(root.leftChild);
            this.copy(root.rightChild);
        }


    }

    @Override
    public void insert(E element) {
     Node<E> newNode = new Node<>(element);
        if (this.root == null) {
            this.root = newNode;
        } else {
            Node<E> current = this.root;
            Node<E> parent = null;
            while (current != null) {
                parent = current;
                if (current.value.compareTo(element) > 0) {
                    current = current.leftChild;
                } else if (current.value.compareTo(element) < 0) {
                    current = current.rightChild;
                } else {
                    return;
                }
            }
            if (parent.value.compareTo(element) > 0) {
                parent.leftChild = newNode;
            } else {
                parent.rightChild = newNode;
            }
        }
    }

    @Override
    public boolean contains(E element) {
       Node<E> current = this.root;
        while (current != null) {
            if (current.value.compareTo(element) > 0) {
                current = current.leftChild;
            } else if (current.value.compareTo(element) < 0) {
                current = current.rightChild;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {

        AbstractBinarySearchTree<E> tree = new BinarySearchTree<>();
        Node<E> current = this.root;
        while (current != null) {
            if (current.value.compareTo(element) > 0) {
                current = current.leftChild;
            } else if (current.value.compareTo(element) < 0) {
                current = current.rightChild;
            } else {
                return new BinarySearchTree<E>(current);
            }
        }

        return tree;
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.left;
    }

    @Override
    public Node<E> getRight() {
        return this.right;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }
}
