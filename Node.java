class Node {
    String data;
    Node next;
    int line;
    int position;
    int length;

    Node(String data, int line, int position, int length) {
        this.data = data;
        next = null;
        this.line = line;
        this.position = position;
    }

}