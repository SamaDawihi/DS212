class LinkedList<T> implements List<T>{
    private Node<T> head;
    private Node<T> current;

    LinkedList(){
        head = current = null;
    }
    
    public boolean empty() {
        return head == null;
    }
    
    public boolean last() {
        return current.next == null;
    }

    public boolean full() {
        return false;
    }

    

    public void findFirst() {
        current = head;
        
    }

    public void findNext() {
        current = current.next;
        
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T value) {
        current.data = value;
        
    }

    public void insert(T value) {
        Node<T> node;
        if(empty()){
            head = current  = new Node<T>(value);
        }
        else{
            node = current.next;
            current.next = new Node<T>(value);
            current = current.next;
            current.next = node;   
        }
    }
    public void remove() {
        if(current == head){
            head = current.next;
        }
        else{
            Node<T> node = head;
            while (node.next != current) 
                node = node.next;
            
            node.next = current.next;
        }
        if(current.next == null) 
            current = head;
        else
            current = current.next;   
    }
    
    //added method from T3
    void reverse(){
        if(head == null || head.next == null) 
            return;
            Node<T>  prev = null, cur = head, next = null;
            while (cur != null){
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            head = prev;
    }
    
}