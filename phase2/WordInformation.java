class WordInformation{
    String word;
    LinkedList<WordOccurrence> occList;
    int size;

    WordInformation(String w, int line, int position) {
        word = w;
        size = 0;
        occList = new LinkedList<WordOccurrence>();
        addOccurrence(line,position);
    }
    void addOccurrence(int line, int position) {
        occList.insert(new WordOccurrence(line,position));
        size++;
    }
   
    String getWord()
    {
        return word;
    }

}