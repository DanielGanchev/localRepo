import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {
    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        private int count;


        public Node(E value) {
            this.value = value;
            this.count = 1;
        }

        public Node(Node<E> otherNode) {

            this.count = otherNode.count;
            this.value = otherNode.getValue();
            if (otherNode.getLeft() != null) {
                this.leftChild = new Node<>(otherNode.getLeft());
            }
            if (otherNode.getRight() != null) {
                this.rightChild = new Node<>(otherNode.getRight());
            }
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }

    public BinarySearchTree(E element) {
        this.root = new Node<>(element);

    }

    public BinarySearchTree(Node<E> otherNode) {
        this.root = new Node<>(otherNode);

    }

    public void eachInOrder(Consumer<E> consumer) {
        nodeInOrder(this.root, consumer);

    }

    private void nodeInOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        nodeInOrder(node.getLeft(), consumer);
        consumer.accept(node.getValue());
        nodeInOrder(node.getRight(), consumer);

    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
        } else {

            insertInto(this.root, element);
        }


    }

    private void insertInto(Node<E> node, E element) {

        if (isGreater(element, node)) {
            if (node.getRight() == null) {
                node.rightChild = new Node<>(element);


            } else {

                insertInto(node.getRight(), element);
            }
        } else if (isLess(element, node)) {
            if (node.getLeft() == null) {
                node.leftChild = new Node<>(element);

            } else {

                insertInto(node.getLeft(), element);
            }
        }
        node.count++;


    }

    private boolean isLess(E element, Node<E> node) {
        return element.compareTo(node.getValue()) < 0;
    }

    private boolean isGreater(E element, Node<E> node) {
        return element.compareTo(node.getValue()) > 0;
    }

    public boolean isEquals(E element, Node<E> node) {
        return element.compareTo(node.getValue()) == 0;
    }

    public boolean contains(E element) {
        Node<E> current = this.root;

        while (current != null) {
            if (isEquals(element, current)) {
                break;
            } else if (isLess(element, current)) {
                current = current.getLeft();
            } else if (isGreater(element, current)) {
                current = current.getRight();
            }
        }

        return current != null;
    }


    public BinarySearchTree<E> search(E element) {
        Node<E> current = this.root;

        while (current != null) {
            if (isEquals(element, current)) {
                break;
            } else if (isLess(element, current)) {
                current = current.getLeft();
            } else if (isGreater(element, current)) {
                current = current.getRight();
            }
        }

        return current != null ? new BinarySearchTree<>(current.getValue()) : null;
    }

    public List<E> range(E first, E second) {
        List<E> result = new ArrayList<>();

        if (this.root == null) {
            return result;

        }

        Deque<Node<E>> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
            if (isLess(first, current) && isGreater(second, current)) {
                result.add(current.getValue());
            } else if (isEquals(first, current) || isEquals(second, current)) {
                result.add(current.getValue());
            }

        }
        return result;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        if (this.root.getLeft() == null) {
            this.root = this.root.getRight();
            return;
        }

        Node<E> current = this.root;

        while (current.getLeft().getLeft() != null) {
            current.count--;
            current = current.getLeft();

        }

        current.count--;
        current.leftChild = current.getLeft().getRight();

    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        if (this.root.getRight() == null) {
            this.root = this.root.getLeft();
            return;
        }

        Node<E> current = this.root;

        while (current.getRight().getRight() != null) {
            current.count--;
            current = current.getRight();
        }
        current.count--;
        current.rightChild = current.getRight().getLeft();


    }

    public int count() {
        return this.root == null ? 0 : this.root.count;
    }

    public int rank(E element) {
        return nodeRank(element, this.root);
    }

    private int nodeRank(E element, Node<E> node) {
        if (node == null) {
            return 0;
        }

        if (isLess(element, node)) {
            return nodeRank(element, node.getLeft());
        } else if (isEquals(element, node)) {
            return node.getLeft() == null ? 0 : node.getLeft().count;
        } else {
            return 1 + node.getLeft().count + nodeRank(element, node.getRight());
        }

    }

    public E ceil(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        Node<E> ceil = null;

        while (current != null) {
            if (isLess(element, current)) {
                ceil = current;
                current = current.getLeft();
            } else if (isGreater(element, current)) {
                current = current.getRight();
            } else {
                Node<E> rightChild = current.getRight();
                if (rightChild != null && ceil != null) {
                    ceil = isLess(rightChild.getValue(), ceil) ? rightChild : ceil;
                } else if (ceil == null) {
                    ceil = rightChild;
                }
                break;
            }
        }

        return ceil != null ? ceil.getValue() : null;
    }

    public E floor(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        Node<E> floor = null;

        while (current != null) {
            if (isLess(element, current)) {
                current = current.getLeft();
            } else if (isGreater(element, current)) {
                floor = current;
                current = current.getRight();
            } else {
                Node<E> leftChild = current.getLeft();
                if (leftChild != null && floor != null) {
                    floor = isGreater(leftChild.getValue(), floor) ? leftChild : floor;
                } else if (floor == null) {
                    floor = leftChild;
                }
                break;
            }
        }

        return floor == null ? null : floor.getValue();

    }
}
