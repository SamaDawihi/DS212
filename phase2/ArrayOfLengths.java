import java.io.*;
class ArrayOfLengths{
    int wordsNumber;
    int uniqueWords;
    LinkedList<WordInformation>[] arrayOfDifferentLengths;
    WordInformation[] sortedArray;

    ArrayOfLengths(String f){
        arrayOfDifferentLengths = (LinkedList<WordInformation>[]) new LinkedList[25];
        for(int i = 0; i < 25; i++){
            arrayOfDifferentLengths[i] = new LinkedList<WordInformation>();
        }
        wordsNumber = 0;
        uniqueWords = 0;
        sortedArray = new WordInformation[50];
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
            BufferedReader reader2 = new BufferedReader(reader); 
            try{
                while(true){
                    if (!word.equals("")){ 
                        boolean exists = false;
                        if(!arrayOfDifferentLengths[word.length()].empty()){ //not the first word of that length
                            arrayOfDifferentLengths[word.length()].findFirst();
                            while(!arrayOfDifferentLengths[word.length()].last()){
                            String s = arrayOfDifferentLengths[word.length()].retrieve().getWord(); //is it exist before
                                if(s.equalsIgnoreCase(word)){
                                    arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                    exists = true;
                                    break; //from while last
                                }
                                arrayOfDifferentLengths[word.length()].findNext();
                            }
                            if(!exists && arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){ //test the last word
                                arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                exists = true;
                            }
                        }
                        if(!exists){
                            WordInformation tmp = new WordInformation(word, lineNo, position);
                            arrayOfDifferentLengths[word.length()].insert(tmp);
                            sortedArray[uniqueWords] = tmp;
                            uniqueWords++;
                        }
                        position++;
                        wordsNumber++;
                        word = "";
                    } //end if word not 
                    
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
                                WordInformation tmp = new WordInformation(word, lineNo, position);
                                arrayOfDifferentLengths[word.length()].insert(tmp);
                                sortedArray[uniqueWords] = tmp;
                                uniqueWords++;
                            }
                            position++;
                            wordsNumber++;
                            word ="" ;
                        }
                        
                        else if ((i != str.length() -1)&&(i != 0)){
                            if((Character.isLetter(str.charAt(i+1))) && (Character.isLetter(str.charAt(i-1)))) 
                                word += str.charAt(i);
                        }
                    }
                }
            }catch(EOFException ex){
                System.out.println("Error" + ex);
            }
            reader2.close();
        }catch (IOException e){
            System.out.println("Error" + e);
        }catch (Exception e){
            System.out.println("Error" + e);
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
                WordInformation tmp = new WordInformation(word, lineNo, position);
                arrayOfDifferentLengths[word.length()].insert(tmp);
                sortedArray[uniqueWords] = tmp;
                uniqueWords++;
            }
            position++;
            wordsNumber++;
            word ="" ;
        }
        for (int i=0 ; i<uniqueWords-1; i++){
            int max = i;
            for (int j=i+1 ; j<uniqueWords; j++){
                if (sortedArray[j].size > sortedArray[max].size)
                max = j;
            }
            WordInformation tmp = sortedArray[i];
            sortedArray[i] = sortedArray[max];
            sortedArray[max] = tmp;
        }
    }//end readFileAndAnalyse(f)
    int documentLength(){//words number
        return wordsNumber;
    }


    int uniqueWords(){//unique words number
        return uniqueWords;
    }

    LinkedList<WordOccurrence> occurrences(String s){//(6) RANA returns list of occurences of word s RANA

            LinkedList<WordOccurrence> occlist = null;
            int l = s.length(); 

            if (!arrayOfDifferentLengths[l].empty()) 
            {
                arrayOfDifferentLengths[l].findFirst();
                while(!arrayOfDifferentLengths[l].last())
                {
                    if (arrayOfDifferentLengths[l].retrieve().getWord().equalsIgnoreCase(s))
                        occlist = arrayOfDifferentLengths[l].retrieve().getOccList() ; 
                    arrayOfDifferentLengths[l].findNext();
                }

                if (arrayOfDifferentLengths[l].retrieve().getWord().equalsIgnoreCase(s))
                    occlist = arrayOfDifferentLengths[l].retrieve().getOccList() ; 
            }
             return occlist;
    }


    int totalWordsForLength(int l){//(4) RANA returns word list in an index l 
        int i = 0;
        if(!arrayOfDifferentLengths[l].empty()){
            i = 1; 
            arrayOfDifferentLengths[l].findFirst();
            while(!arrayOfDifferentLengths[l].last()){
                i++;
                arrayOfDifferentLengths[l].findNext();
            }
        }
        return i ; 
    } 


    boolean checkAdjacent(String w1, String w2){
            if(arrayOfDifferentLengths[w1.length()].empty() || arrayOfDifferentLengths[w2.length()].empty())// check not empty()
                return false;
            LinkedList<WordOccurrence> w1occ = occurrences(w1); //temp list to store word 1&2 occurrences
            LinkedList<WordOccurrence> w2occ = occurrences(w2);
            
            int l1,l2,p1,p2;

            w1occ.findFirst();
            l1 = w1occ.retrieve().getLine();
            p1 = w1occ.retrieve().getPosition();
            
            w2occ.findFirst();
            l2 = w2occ.retrieve().getLine();
            p2 = w2occ.retrieve().getPosition();


            while(!w1occ.last() || !w2occ.last()){

                if(l1 == l2 && Math.abs(p1 - p2) == 1)
                    return true;
                
                if((w2occ.last() || l2 > l1 || p2 > p1) && !w1occ.last()){
                    w1occ.findNext();
                    l1 = w1occ.retrieve().getLine();
                    p1 = w1occ.retrieve().getPosition();
                }
                
                if((w1occ.last() ||( l2 <= l1 && p2 <= p1)) && !w2occ.last()){
                    w2occ.findNext();
                    l2 = w2occ.retrieve().getLine();
                    p2 = w2occ.retrieve().getPosition();
                }
            }
            if(l1 == l2){
                if(Math.abs(p1 - p2) == 1)
                    return true;
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
        System.out.println("**************");
        for (int i=0 ; i<uniqueWords ; i++){
            sortedArray[i].printInfo();
        }
        System.out.println("-------------------------------");
        LinkedList<WordOccurrence> occlist = occurrences("data");
        occlist.findFirst();
        while(!occlist.last()){
            occlist.retrieve().printOcc();
            occlist.findNext();
        }
        System.out.println("test adjacent Method");
        


    }

}