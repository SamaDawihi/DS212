class Node1 {
    String data;
    Node1 next;
    int line;
    int position;
    int length;
    boolean unique;
    int occurrence;

    Node1(String data, int line, int position, int length) {
        this.data = data;
        next = null;
        this.line = line;
        this.position = position;
        this.length = length;
        unique = true;
        occurrence = 0;
    }
    boolean equals(Node1 other){
        return(data.equalsIgnoreCase(other.data));
    }
    boolean same(Node1 other){
        return ((line == other.line) && (position == other.position));
    }
    void print(){
        System.out.println("line: (" + line + ") position: (" + position +"\nword: (" + data + ") length: (" + length + "\nisUnique: (" + unique +") occurrence: ("+ occurrence + "\n----------------------------------");
    }

}