class testArrayOfLengths{
    public static void main(String[] args) {
        ArrayOfLengths arr = new ArrayOfLengths("file2.txt");
        arr.printArray();
        System.out.println("word1 = 'data', word2 = 'values' result: " + arr.checkAdjacent("functions", "the"));
        System.out.println("END");

    }
}