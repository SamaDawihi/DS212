import java.io.*;
class ArrayOfLengths{
    int wordsNumber;
    int uniqueWords;
    LinkedList<WordInformation>[] arrayOfDifferentLengths;

    ArrayOfLengths(String f){
        arrayOfDifferentLengths = (LinkedList<WordInformation>[]) new LinkedList<?>[25];
        for(int i = 0; i < 25; i++){
            arrayOfDifferentLengths[i] = new LinkedList<WordInformation>();
        }
        wordsNumber = 0;
        uniqueWords = 0;
        readFileAndAnalyse(f);
    }
    void readFileAndAnalyse(String f){
        int lineNo = 0;
        int position = 0;
        String str = "";
        String word = ""; 

        try{
            File file = new File(f);
            FileReader reader = new FileReader(file);
            BufferedReader reader2 = new BufferedReader(reader); //
            try{
                while(true){
                    if (!word.equals("")){ 
                        boolean exists = false;
                        if(!arrayOfDifferentLengths[word.length()].empty()){
                            arrayOfDifferentLengths[word.length()].findFirst();
                            while(!arrayOfDifferentLengths[word.length()].last()){
                            String s = arrayOfDifferentLengths[word.length()].retrieve().getWord();
                                if(s.equalsIgnoreCase(word)){
                                    arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                    exists = true;
                                    break;
                                }
                                arrayOfDifferentLengths[word.length()].findNext();
                            }
                            if(!exists && arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){
                                arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                exists = true;
                            }
                        }
                        if(!exists){
                            arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
                            uniqueWords++;
                        }
                        position++;
                        wordsNumber++;
                        word = "";
                    }
                    
                    str = reader2.readLine();
                    if(str == null) break; 
                    lineNo++;
                    position = 1;

                    for(int i = 0; i < str.length(); i++){ 

                        if(Character.isLetter(str.charAt(i))){ 
                            word += str.charAt(i);
                        }

                        else if(Character.isWhitespace(str.charAt(i))){
                            boolean exists = false;
                            if(!arrayOfDifferentLengths[word.length()].empty()){
                                arrayOfDifferentLengths[word.length()].findFirst();
                                while(!arrayOfDifferentLengths[word.length()].last()){
                                    String s = arrayOfDifferentLengths[word.length()].retrieve().getWord();
                                    if(s.equalsIgnoreCase(word)){
                                        arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                        exists = true;
                                        break;
                                    }
                                    arrayOfDifferentLengths[word.length()].findNext();
                                }
                                if(!exists && arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){
                                    arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                    exists = true;
                                }
                            }
                            if (!exists){
                                arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
                                uniqueWords++;
                            }
                            position++;
                            wordsNumber++;
                            word = "";
                        }
                        
                        else if ((i != str.length() -1)&&(i != 0)){//يشيك ان مب بدايةالسطر او نهايتها
                            if((Character.isLetter(str.charAt(i+1))) && (Character.isLetter(str.charAt(i-1)))) //اذا بداية الكلمة قبلها سبيس
                                word += str.charAt(i);
                        }
                    }
                }
            }catch(EOFException ex){
                System.out.println("Error: "+ ex);
            }
            reader2.close();
        }catch (IOException e){
            System.out.println("Error: "+ e);
        }catch (Exception e){
            System.out.println("Error: "+ e);
        }
        
        if (!word.equals("")){ 
            boolean exists = false;
            if(!arrayOfDifferentLengths[word.length()].empty()){
                arrayOfDifferentLengths[word.length()].findFirst();
                while(!arrayOfDifferentLengths[word.length()].last()){
                    String s = arrayOfDifferentLengths[word.length()].retrieve().getWord();
                    if(s.equalsIgnoreCase(word)){
                        arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                        exists = true;
                        break;
                    }
                    arrayOfDifferentLengths[word.length()].findNext();
                }
                if(!exists && arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){
                    arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                    exists = true;
                }
            }
            if (!exists){
                arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
                uniqueWords++;
            }
            position++;
            wordsNumber++;
            word = "";
        }
    }
    int documentLength(){//words number
        return wordsNumber;
    }


    int uniqueWords(){//unique words number
        return uniqueWords;
    }
    void totalWord(String s){//occurences of word s
    }
    void totalWordsForLength(int l){//

    }

    boolean checkAdjacent(String w1, String w2){
            if(arrayOfDifferentLengths[w1.length()].empty() || arrayOfDifferentLengths[w2.length()].empty())// check not empty()
                return false;

            LinkedList<WordOccurrence> w1occ = new LinkedList<WordOccurrence>(); //temp list to store word 1&2 occurrences
            LinkedList<WordOccurrence> w2occ = new LinkedList<WordOccurrence>();
            int size1 = 0, size2 = 0; //sizes of w1occ and w2occ

            //find w1 occ list
            arrayOfDifferentLengths[w1.length()].findFirst();
            while(!arrayOfDifferentLengths[w1.length()].last()){
                if(w1.equalsIgnoreCase(arrayOfDifferentLengths[w1.length()].retrieve().getWord())){
                    size1 = arrayOfDifferentLengths[w1.length()].retrieve().getSize();
                    w1occ = arrayOfDifferentLengths[w1.length()].retrieve().getOccList();
                    break;
                }
                arrayOfDifferentLengths[w1.length()].findNext();
            }
            //check last element
            if(w1.equalsIgnoreCase(arrayOfDifferentLengths[w1.length()].retrieve().getWord())){
                size1 = arrayOfDifferentLengths[w1.length()].retrieve().getSize();
                w1occ = arrayOfDifferentLengths[w1.length()].retrieve().getOccList();
            }
            //end of find w1 occ list

            //find w2 occ list
            arrayOfDifferentLengths[w2.length()].findFirst();
            while(!arrayOfDifferentLengths[w2.length()].last()){
                if(w2.equalsIgnoreCase(arrayOfDifferentLengths[w2.length()].retrieve().getWord())){
                    size2 = arrayOfDifferentLengths[w2.length()].retrieve().getSize();
                    w2occ = arrayOfDifferentLengths[w2.length()].retrieve().getOccList();
                    break;
                }
                arrayOfDifferentLengths[w2.length()].findNext();
            }
            //check last elemnt
            if(w2.equalsIgnoreCase(arrayOfDifferentLengths[w2.length()].retrieve().getWord())){
                size2 = arrayOfDifferentLengths[w2.length()].retrieve().getSize();
                w2occ = arrayOfDifferentLengths[w2.length()].retrieve().getOccList();
            }
            //end of find w1 occ list

            //checking adj
            w1occ.findFirst();
            for(int n = 0; n < size1; n++) {
                w2occ.findFirst();
                for(int i=0; i < size2; i++){
                    if(w1occ.retrieve().getLine() == w2occ.retrieve().getLine()){
                        if(Math.abs(w1occ.retrieve().getPosition() -  w2occ.retrieve().getPosition()) == 1)
                            return true;
                    }
                    w2occ.findNext();
                }
                w1occ.findNext();
            }



        return false;
    }

    void printArray(){
        System.out.println("Print Method");
        System.out.println("words: " + documentLength() + " unique words: " + uniqueWords());
        for(int i=1; i<12; i++){
            if(!arrayOfDifferentLengths[i].empty()){
                arrayOfDifferentLengths[i].findFirst();
                System.out.println("------index: " + i + " ------");
                while(!arrayOfDifferentLengths[i].last()){
                    arrayOfDifferentLengths[i].retrieve().printInfo();
                    System.out.println("-------------------------------");
                    arrayOfDifferentLengths[i].findNext();
                }
                arrayOfDifferentLengths[i].retrieve().printInfo();
                System.out.println("-------------------------------");
            }
        }
        System.out.println("-------------------------------");
        System.out.println("test adjacent Method");
        System.out.println("word1 = operations, word2 = or result: " + checkAdjacent("operations", "or"));


    }

}