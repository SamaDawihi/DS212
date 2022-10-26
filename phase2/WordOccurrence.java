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
    boolean isAdjacent(WordOccurrence occ){
        if (lineNo == occ.getLine()){
            if(Math.abs(occ.getPosition() - position) == 1)
                return true;
        }
        return false;
    }
    boolean sameLine(WordOccurrence occ){
        return lineNo == occ.getLine();
    }
}