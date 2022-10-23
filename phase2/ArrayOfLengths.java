import java.io.*;
class ArrayOfLengths{
    int wordsNumber;
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
                        insert(word,lineNo,++position);
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
                            insert(word,lineNo,++position);
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
        }catch (IOException e){
            System.out.println("Error: "+ e);
        }catch (Exception e){
            System.out.println("Error: "+ e);
        }
        
       if (!word.equals("")){ //مانستفيد منها يمكن
        insert(word,lineNo,++position);
            word = "";
        }
    }
    
    void insert(String word, int lineNo, int position){
        arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));//تتاكد الكلمة موجودة قبل او لا
        
        arrayOfDifferentLengths[word.length()].insert(new WordInformation(word, lineNo, position));
        wordsNumber++;
        
    }
    int documentLength(){//words number
        return wordsNumber;
    }
    void uniqueWords(){//unique words number
    }
    void totalWord(String s){//occurences of word s
    }
    void totalWordsForLength(int l){//

    }

}