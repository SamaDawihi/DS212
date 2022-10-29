import java.io.*;
import java.util.Scanner;
class ArrayOfLengths{
    Scanner input = new Scanner(System.in);
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
                            if(word != ""){
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
                                    uniqueWords++;
                                }
                                position++;
                                wordsNumber++;
                                word ="" ;
                            }
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
                uniqueWords++;
            }
            position++;
            wordsNumber++;
            word ="" ;
        }
        sortedArray = new WordInformation[uniqueWords];

        int index = 0; 
        for (int i=0; i<arrayOfDifferentLengths.length;i++){// add words to sorted
            if(!arrayOfDifferentLengths[i].empty()){
            arrayOfDifferentLengths[i].findFirst();
            while(!arrayOfDifferentLengths[i].last()){
                sortedArray[index++] = arrayOfDifferentLengths[i].retrieve();
                arrayOfDifferentLengths[i].findNext();
            }
            sortedArray[index++]= arrayOfDifferentLengths[i].retrieve();
        }//end if 
        }//end for 

        for (int i=0 ; i<sortedArray.length-1; i++){// sort sorted
            int max = i;
            for (int j=i+1 ; j<sortedArray.length; j++){
                if (sortedArray[j].size > sortedArray[max].size)
                max = j;
            }
            WordInformation tmp = sortedArray[i];
            sortedArray[i] = sortedArray[max];
            sortedArray[max] = tmp;
        }
    }//end readFileAndAnalyse(f)
    
    int documentLength(){//Operation(1) O(1)
        return wordsNumber;
    }

    int uniqueWords(){//unique words number //Operation(2) O(1)
        return uniqueWords;
    }
    
    int totalWords(String s){ //Operation(3) case1: O(m/k) or case2: O(m),O(n)
        if (!arrayOfDifferentLengths[s.length()].empty()){
        arrayOfDifferentLengths[s.length()].findFirst();
        while (!arrayOfDifferentLengths[s.length()].last()){
            if (arrayOfDifferentLengths[s.length()].retrieve().word.equalsIgnoreCase(s))
                return arrayOfDifferentLengths[s.length()].retrieve().size;
                arrayOfDifferentLengths[s.length()].findNext();
        }
        if (arrayOfDifferentLengths[s.length()].retrieve().word.equalsIgnoreCase(s)) //last
                return arrayOfDifferentLengths[s.length()].retrieve().size;
        }
    return 0 ;
    }

    int totalWordsForLength(int l){//Operation(4) O(1) 
        
        return arrayOfDifferentLengths[l].length() ; 
    } 

    void displayUniqueWords (){//Operation(5) O(m)
        for (int i=0 ; i < uniqueWords-1 ; i++){
            System.out.print("("+sortedArray[i].word+","+sortedArray[i].size+"),");
        }
        System.out.println("("+sortedArray[uniqueWords-1].word+","+sortedArray[uniqueWords-1].size+")");
    }

    LinkedList<WordOccurrence> occurrences(String s){//Operation(6) O(n)

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

    boolean checkAdjacent(String w1, String w2){//Operation(7) O(n)
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
            if(l1 == l2 && Math.abs(p1 - p2) == 1)
                return true;
            
            return false;     
        }
    

    void printArray(){
        System.out.println("words: " + documentLength() + " unique words: " + uniqueWords());
        System.out.println("**************");
        System.out.println("unique word with occurrences from most to least:");
        displayUniqueWords();
        System.out.println("**************");
        System.out.println("enter a word you want it number of occurrences:");
        String str = input.next();
        System.out.println(totalWords(str));
        System.out.println("**************");
        System.out.println("enter a length to display the number of words of that length:");
        int len = input.nextInt();
        System.out.println(totalWordsForLength(len));
        System.out.println("**************");
        System.out.println("enter a word to know the locations of its occurrences:");
        str = input.next();
        LinkedList<WordOccurrence> occlist = occurrences(str);
        if (occlist!=null){
            occlist.findFirst();
            while(!occlist.last()){
                occlist.retrieve().printOcc();
                occlist.findNext();
            }
            occlist.retrieve().printOcc();
        }
        else
        System.out.println("not found");
        System.out.println("**************");
        System.out.println("test if two words are adjecent:");
        String s1 = input.next(); String s2 = input.next();
        System.out.println(checkAdjacent(s1,s2));
    }//end print 
      
}