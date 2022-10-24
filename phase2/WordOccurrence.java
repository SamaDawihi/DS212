class WordOccurrence{
    int lineNo;
    int position;

    WordOccurrence(int l, int p){
        lineNo = l;
        position = p;
    }
    public int getLine() {
        return lineNo;
    }
    public int getPosition() {
        return position;
    }
    void printOcc(){
        System.out.println("line: " + lineNo + " position: " + position);
    }
}