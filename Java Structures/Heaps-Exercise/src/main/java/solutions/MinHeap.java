package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {


    private List<E> data;

    public MinHeap() {
        this.data = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public void add(E element) {
        this.data.add(element);
        this.heapifyUp(this.data.size() - 1);

    }

    private void heapifyUp(int i) {
        int parent = this.getParentIndexFor(i);
        while (i > 0 && isLess(i, parent)) {
            Collections.swap(this.data, i, parent);
            i = parent;
            parent = this.getParentIndexFor(i);
        }
    }

    private int getParentIndexFor(int i) {
        return (i - 1) / 2;
    }


    private boolean isLess(int i, int parent) {
        return this.data.get(i).compareTo(this.data.get(parent)) < 0;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.data.get(0);
    }

    private void ensureNonEmpty() {
        if (this.data.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.data.get(0);
        Collections.swap(this.data, 0, this.data.size() - 1);
        this.data.remove(this.data.size() - 1);
        this.heapifyDown(0);
        return element;
    }

    private void heapifyDown(int i) {
        while (i < this.data.size() / 2) {
            int child = this.getLeftChildIndex(i);
            if (hasRightChild(i) && isLess(this.getRightChildIndex(i), child)) {
                child = this.getRightChildIndex(i);
            }
            if (isLess(child, i)) {
                Collections.swap(this.data, i, child);
                i = child;
            } else {
                break;
            }
        }
    }

    private boolean hasRightChild(int i) {
        return this.getRightChildIndex(i) < this.data.size();
    }

    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    @Override
    public void decrease(E element) {

        int index = this.data.indexOf(element);
        E heapElement = this.data.get(index);
        heapElement.decrease();
        this.heapifyUp(index);





    }
}
