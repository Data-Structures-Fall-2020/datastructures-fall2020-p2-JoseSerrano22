package edu.uprm.cse.ds.sortedlist;


import java.util.Iterator;

public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {

    private static class Node<E> { //copy and paste of the DoubleLinkedQueue from classroom

        private E element; // reference to value stored in the Node
        private Node<E> next;  // reference to next Node in the chain
        private Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            this.element = null;
            this.next = null;
            this.prev = null;
        }

        public E getElement() { return element; }

        public void setElement(E element) { this.element = element; }

        public Node<E> getNext() { return next; }

        public void setNext(Node<E> next) { this.next = next; }

        public Node<E> getPrev() { return prev; }

        public void setPrev(Node<E> prev) { this.prev = prev; }

    }


    private Node<E> header;
    private int currentSize;

    public SortedCircularDoublyLinkedList(){ //constructor
        this.currentSize = 0;
        this.header = new Node<E>();
    }


    @Override
    public boolean add(E obj) {
        Node<E> newNode = new Node<>(obj,null,null);

        if(isEmpty()) {
            this.header.setNext(newNode);
            newNode.setNext(header);
            this.header.setPrev(newNode);
            newNode.setPrev(header);

            this.currentSize++;
            return true;

        } else if (this.header.getNext().getElement().compareTo(obj) >=0){
            newNode.setNext(this.header.getNext());
            newNode.setPrev(this.header);
            this.header.getNext().setPrev(newNode);
            this.header.setNext(newNode);

            this.currentSize++;
            return true;

        } else if (this.header.getPrev().getElement().compareTo(obj) <=0){
            newNode.setNext(this.header);
            newNode.setPrev(header.getPrev());
            this.header.getPrev().setNext(newNode);
            this.header.setPrev(newNode);

            this.currentSize++;
            return true;

        }  else {
            Node<E> temp1;
            Node<E> temp2 = header.getNext();

            for(temp1 = header.getNext().getNext(); temp1!=null; temp1 = temp1.getNext()){
                if(temp2.getElement().compareTo(obj) <=0 && temp1.getElement().compareTo(obj) >=0){

                    temp2.setNext(newNode);
                    newNode.setPrev(temp2);
                    newNode.setNext(temp1);
                    temp1.setPrev(newNode);

                    this.currentSize++;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.currentSize;
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

        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("small or big");
        }

        Node<E> target = this.getPosition(index);

        return target.getElement();
    }

    @Override
    public void clear() {
        while (!this.isEmpty()) {
            this.remove(0);
        }
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public Iterator<E> iterator(int index) {
        return null;
    }

    @Override
    public int firstIndex(E e) {
        int index = 0;

        for( Node<E> temp1 = header.getNext(); temp1!=null; temp1 = temp1.getNext(), index++){
            if (temp1.getElement().equals(e)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndex(E e) {
        int index = 0;
        int result =-1;

        for( Node<E> temp1 = header.getNext(); temp1!=null; temp1 = temp1.getNext(), index++){
            if (temp1.getElement().equals(e)) {
                result = index;
            }
        }

        return result;
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


    private Node<E> getPosition(int index) {

        int currentPosition = 0;
        Node<E> temp = this.header.getNext();

        while ((currentPosition < index)) {
            temp = temp.getNext();
            currentPosition++;
        }

        return temp;
    }
}