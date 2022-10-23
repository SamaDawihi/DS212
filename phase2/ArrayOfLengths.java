import java.io.*;
class ArrayOfLengths{
    int wordsNumber;
    int uniqueWords;
    LinkedList<WordInformation>[] arrayOfDifferentLengths;

    ArrayOfLengths(String f){
        arrayOfDifferentLengths = (LinkedList<WordInformation>[]) new Object[50];
        for(int i = 0; i < 50; i++){
            arrayOfDifferentLengths[i] = new LinkedList<WordInformation>();
        }
        wordsNumber = 0;
        readFileAndAnalyse(f);

    }
    void readFileAndAnalyse(String f){
        int lineNo = 0;
        int position = 0;
        String str = ""; //كل سطر
        String word = ""; //كل كلمة

        try{
            File file = new File(f);
            FileReader reader = new FileReader(file);
            BufferedReader reader2 = new BufferedReader(reader); //i a-m a student/n in king saud uni
            try{
                while(true){


                    if (!word.equals("")){ // عشان يضيف اخر كلمة بالسطر
                        boolean exists = false;
                            arrayOfDifferentLengths[word.length()].findFirst();//تتاكد الكلمة موجودة قبل او لا
                            while(!arrayOfDifferentLengths[word.length()].last()){
                            String s = arrayOfDifferentLengths[word.length()].retrieve().getWord();
                                if(s.equalsIgnoreCase(word)){
                                    arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                    exists = true;
                                    break;
                                }
                            }
                            if(arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){
                                arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                exists = true;
                            }
                            if (!exists){
                                arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
                                uniqueWords++;
                            }
                            wordsNumber++;
                            word = "";
                    }
                    str = reader2.readLine();
                    if(str == null) break; //وصلنا اخر السطر
                    lineNo++;
                    position = 0;

                    for(int i = 0; i < str.length(); i++){ //نمشي على حرف حرف

                        if(Character.isLetter(str.charAt(i))){ // حرف
                            word += str.charAt(i);
                        }

                        else if(Character.isWhitespace(str.charAt(i))){
                            boolean exists = false;
                            arrayOfDifferentLengths[word.length()].findFirst();//تتاكد الكلمة موجودة قبل او لا
                            while(!arrayOfDifferentLengths[word.length()].last()){
                            String s = arrayOfDifferentLengths[word.length()].retrieve().getWord();
                                if(s.equalsIgnoreCase(word)){
                                    arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                    exists = true;
                                    break;
                                }
                            }
                            if(arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){
                                arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                                exists = true;
                            }
                            if (!exists)
                                arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
                            
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
        
       if (!word.equals("")){ //مانستفيد منها يمكن
        boolean exists = false;
        arrayOfDifferentLengths[word.length()].findFirst();//تتاكد الكلمة موجودة قبل او لا
        while(!arrayOfDifferentLengths[word.length()].last()){
           String s = arrayOfDifferentLengths[word.length()].retrieve().getWord();
            if(s.equalsIgnoreCase(word)){
                arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
                exists = true;
                break;
            }
            arrayOfDifferentLengths[word.length()].findNext();

        }
        if(arrayOfDifferentLengths[word.length()].retrieve().getWord().equalsIgnoreCase(word)){
            arrayOfDifferentLengths[word.length()].retrieve().addOccurrence(lineNo,position);
            exists = true;
        }
        if (!exists)
            arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
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

    void print(){
        LinkedList<WordOccurrence> C = new LinkedList<WordOccurrence>();

        for(int i=1; i<10; i++){
            System.out.println("------index: " + i + " ------");
            while(!arrayOfDifferentLengths[i].empty() && !arrayOfDifferentLengths[i].last()){
                System.out.println("word: " + arrayOfDifferentLengths[i].retrieve().getWord() );
                System.out.println("occurences:  " + arrayOfDifferentLengths[i].retrieve().getSize());
                System.out.println("-------------------------------");
                
                arrayOfDifferentLengths[i].findNext();
            }
            System.out.println("word: " + arrayOfDifferentLengths[i].retrieve().getWord() );
            System.out.println("occurences:  " + arrayOfDifferentLengths[i].retrieve().getSize());
            System.out.println("-------------------------------");
        }
    }

}