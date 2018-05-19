
import java.util.*;
public class Assignment1Q2 {
    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(3);
        Node<Integer> n2 = new Node<>(8, n1);
        Node<Integer> n3 = new Node<>(9, n2);
        Node<Integer> n4 = new Node<>(7, n3);
        Node<Integer> n5 = new Node<>(5, n4);
        Node<Integer> n6 = new Node<>(2, n5);
        Node<Integer> n7 = new Node<>(0, n6);

        System.out.println(kthToLastElement(n7, 3).getData());

    }
    public static Node kthToLastElement(Node head, int k){
        if(head==null)
            return null;
        if(k<0)
            return null;
        Node forward = head;
        Node current = head;
        for(int i=0;i<k;i++){
            forward=forward.getNext();
            if(forward==null)
                return null;
        }
        while(forward.getNext()!=null){
            forward=forward.getNext();
            current=current.getNext();
        }
        return current;

    }
}
class Node<T>{
    private T data;
    private Node<T> next;
    public Node(T data_){
        this.data=data_;
        this.next=null;
    }
    public Node(T data_, Node<T> next_){
        this.data=data_;
        this.next=next_;
    }
    public Node getNext(){
        return this.next;
    }

    public T getData() {
        return this.data;
    }

    public void setNext(Node<T> next_) {
        this.next = next_;
    }
    public void setData(T data_){
        this.data=data;
    }
}

