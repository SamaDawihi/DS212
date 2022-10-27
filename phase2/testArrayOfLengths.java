class testArrayOfLengths{
    public static void main(String[] args) {
        ArrayOfLengths shk = new ArrayOfLengths("file2.txt");
        shk.printArray();
        System.out.println("total words: " + shk.totalWordsForLength(1));
        System.out.println("END");

    }
}