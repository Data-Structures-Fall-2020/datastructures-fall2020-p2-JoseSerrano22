package edu.uprm.cse.ds.sortedlist;


import java.util.Iterator;

public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {


    @Override
    public boolean add(E obj) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean remove(E obj) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public int removeAll(E obj) {
        return 0;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator(int index) {
        return null;
    }

    @Override
    public int firstIndex(E e) {
        return 0;
    }

    @Override
    public int lastIndex(E e) {
        return 0;
    }

    @Override
    public ReverseIterator<E> reverseIterator() {
        return null;
    }

    @Override
    public ReverseIterator<E> reverseIterator(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
