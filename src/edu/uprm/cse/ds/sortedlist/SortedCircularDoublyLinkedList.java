package edu.uprm.cse.ds.sortedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

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


    private final Node<E> header;
    private int currentSize;

    public SortedCircularDoublyLinkedList(){ //constructor
        this.currentSize = 0;
        this.header = new Node<>();
    }


    @Override
    public boolean add(E obj) { //add element in ascending order
        Node<E> newNode = new Node<>(obj,null,null);

        if(isEmpty()) { // if empty just add
            this.header.setNext(newNode);
            newNode.setNext(header);
            this.header.setPrev(newNode);
            newNode.setPrev(header);

            this.currentSize++;
            return true;

        } else if (this.header.getNext().getElement().compareTo(obj) >=0){ // if the element next to header is greater than the element outside, add it next the header
            newNode.setNext(this.header.getNext());
            newNode.setPrev(this.header);
            this.header.getNext().setPrev(newNode);
            this.header.setNext(newNode);

            this.currentSize++;
            return true;

        } else if (this.header.getPrev().getElement().compareTo(obj) <=0){ // if the element before the header is below than the element outside, add it before the header
            newNode.setNext(this.header);
            newNode.setPrev(header.getPrev());
            this.header.getPrev().setNext(newNode);
            this.header.setPrev(newNode);

            this.currentSize++;
            return true;

        }  else { // add it between the large and low element comparing the element outside and add it
            Node<E> temp1 = header.getNext();
            Node<E> temp2;

            for(temp2 = temp1.getNext(); temp2!=null; temp2 = temp2.getNext(), temp1 = temp1.getNext()){
                if(temp1.getElement().compareTo(obj) <=0 && temp2.getElement().compareTo(obj) >=0){

                    temp1.setNext(newNode);
                    newNode.setPrev(temp1);
                    newNode.setNext(temp2);
                    temp2.setPrev(newNode);

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
    public boolean remove(E obj) { // remove the first object in the list if not the firstIndex() will return -1 becoming false the function
        if(!this.isEmpty() && firstIndex(obj)!=-1){
            remove(firstIndex(obj));
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int index) { // remove the object on that index

        if (index < 0 || index >= this.size()) { // if is bellow or greater than the size return false
           return false;
        } else{
            Node<E> temp, target;
            if(index==0){ //if the index is at the start the temp is the header
                temp = this.header;
            } else{
                temp = this.getPosition(index-1); //if the index is not at the start get the position before the index for the temp
            }
            // the target is the one that we want to eliminate and is the element after the temp
            //replace the link between the temp and target and change it for temp and target.getNext
            target = temp.getNext();
            target.getNext().setPrev(temp);
            temp.setNext(target.getNext());
            target.setNext(null);
            target.setPrev(null);
            target.setElement(null);
            this.currentSize--;
            return true;
        }

    }

    @Override
    public int removeAll(E obj) { //call the function for remove the first object until there is not more that object
        int counter = 0;

        while (this.remove(obj)) {
            counter++;
        }
        return counter;
    }

    @Override
    public E first() { // lowest element
        return this.header.getNext().getElement();
    }

    @Override
    public E last() { // largest element
        return this.header.getPrev().getElement();
    }

    @Override
    public E get(int index) { // get the element in that index of the list

        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("small or big");
        }

        Node<E> target = this.getPosition(index);

        return target.getElement();
    }

    @Override
    public void clear() { // remove the first index until the size==0
        while (!this.isEmpty()) {
            this.remove(0);
        }
    }

    @Override
    public boolean contains(E e) { // find the first index of the list to check if the element exist

        return firstIndex(e) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public int firstIndex(E e) { // run the list until the element appear
        int index = 0;
        Node<E> temp = this.header.getNext();

        while ((index < this.size())) {
            if(temp.getElement().equals(e)){
                return index;
            }
            temp = temp.getNext();
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndex(E e) { // run the list until the last element we want appear
        int index = 0;
        int result =-1;

        Node<E> temp = this.header.getNext();

        while ((index < this.size())) {
            if(temp.getElement().equals(e)){
                result=index;
            }
            temp = temp.getNext();
            index++;
        }

        return result;
    }
    @Override
    public ReverseIterator<E> reverseIterator() { return new ReverseListIterator<>(); }

    @Override
    public ReverseIterator<E> reverseIterator(int index) { return new ReverseListIterator<>(index); }

    @Override
    public Iterator<E> iterator() { return new ListIterator<>(); }

    @Override
    public Iterator<E> iterator(int index) { return new ListIterator<>(index); }

    private Node<E> getPosition(int index) { //find the node in that position

        int currentPosition = 0;
        Node<E> temp = this.header.getNext();

        while ((currentPosition < index)) {
            temp = temp.getNext();
            currentPosition++;
        }

        return temp;
    }

    private class ReverseListIterator<E> implements ReverseIterator<E> {

        private Node<E> newNode;

        //Constructor for the reverse iterator
        public ReverseListIterator() {
            this.newNode = (Node<E>) header;
        }

        //Constructor with  parameters
        public ReverseListIterator(int index) {
            int i = 0;
            this.newNode = (Node<E>) header;
            while (i < index) {
                this.newNode = this.newNode.getPrev();
                i++;
            }

        }

        //Checks if there is a next element in the list
        @Override
        public boolean hasPrevious() {
            return this.newNode.getPrev() != header;
        }

        //Gets the previous element, if is not possible,
        //throws an NoSuchElementException
        @Override
        public E previous() {
            if (this.hasPrevious()) {
                E result = this.newNode.getPrev().getElement();
                this.newNode = this.newNode.getPrev();
                return result;
            }else {
                throw new NoSuchElementException();
            }
        }

    }

    private class ListIterator<E> implements Iterator<E> {

        private Node<E> newNode;

        //Constructor for the iterator
        public ListIterator() {
            this.newNode = (Node<E>) header;
        }

        //Constructor with parameters
        public ListIterator(int index) {
            int i = 0;
            this.newNode = (Node<E>) header;
            while (i < index) {
                this.newNode = this.newNode.getNext();
                i++;
            }
        }


        //Checks if there is a next element in the list
        @Override
        public boolean hasNext() {
			return this.newNode.getNext() != header;
        }

        //Gets the next element, if is not possible,
        //throws an NoSuchElementException
        @Override
        public E next() {
            if (this.hasNext()) {
                E result = this.newNode.getNext().getElement();
                this.newNode = this.newNode.getNext();
                return result;
            }else {
                throw new NoSuchElementException();
            }
        }

    }


}