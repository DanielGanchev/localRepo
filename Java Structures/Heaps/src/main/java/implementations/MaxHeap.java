package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private List<E> elements;

    public MaxHeap() {
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
       if (this.size() == 0) {
           throw new IllegalStateException("Heap is empty upon peek attempt");
       }
         return this.elements.get(0);
    }
}
