package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int i) {
        int parent = this.getParent(i);
        while (i > 0 && this.isGreater(i, parent)) {
            Collections.swap(this.elements, i, parent);
            i = parent;
            parent = this.getParent(i);
        }
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private boolean isGreater(int i, int parent) {
        return this.elements.get(i).compareTo(this.elements.get(parent)) > 0;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.elements.get(0);
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("Heap is empty upon peek attempt");
        }
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.elements.get(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.size() - 1);
        this.heapifyDown(0);
        return element;
    }

    private void heapifyDown(int i) {
        while (i < this.size() / 2) {
            int child = this.getLeft(i);
            if (this.hasRight(i) && this.isLess(child, this.getRight(i))) {
                child = this.getRight(i);
            }
            if (this.isLess(i, child)) {
                Collections.swap(this.elements, i, child);
                i = child;
            } else {
                break;
            }
        }
    }

    private boolean isLess(int child, int right) {
        return this.elements.get(child).compareTo(this.elements.get(right)) < 0;
    }

    private boolean hasRight(int i) {
        return this.getRight(i) < this.size();
    }


    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRight(int i) {
        return 2 * i + 2;
    }
}
