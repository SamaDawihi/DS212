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
        this.length = length;
    }
    boolean equals(Node other){
        return(data.equals(other.data));
    }
    boolean same(Node other){
        return ((line == other.line) && (position == other.position));
    }
    void print(){
        System.out.println("line: " + line + " position: " + position +"\nword: " + data + " length: " + length +"\n----------------------------------");
    }

}