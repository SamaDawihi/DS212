class Node {
    String data;
    Node next;
    int line;
    int position;

    Node(String data, int line, int position) {
        this.data = data;
        next = null;
        this.line = line;
        this.position = position;
    }
    
}