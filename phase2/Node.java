package phase2;
public class Node<T>{
    T data;
    Node<T> next;

    Node() {
        data = null;
        next = null;
    }
    Node (T val){
        data = val;
        next = null;
    }
}