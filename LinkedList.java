import java.io.*;
import java.util.Scanner;
public class LinkedList {
    private Node head;
    private Node current;
    private int length;

    LinkedList(String path){
        addFile(path);
        calculateOccurance();
 
    }
    void addFile(String path){
        int line = 0;
        int position = 0;
        String str = ""; //كل سطر
        String word = ""; //كل كلمة

        try{
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader reader2 = new BufferedReader(reader);
            try{
                while(true){


                    if (!word.equals("")){ //////////////////////
                        insert(word,line,++position);
                            word = "";
                    }
                    str = reader2.readLine();
                    //if(str == null) break; 
                    line++;
                    position = 0;

                    for(int i = 0; i < str.length(); i++){

                        if(Character.isLetter(str.charAt(i))){
                            word += str.charAt(i);
                        }

                        else if(Character.isWhitespace(str.charAt(i))){
                            insert(word,line,++position);
                            word = "";
                        }
                        else if ((i != str.length() -1)&&(i != 0)){//يشيك ان مب بداية الجملة او نهايتها

                            if((Character.isLetter(str.charAt(i+1))) && (Character.isLetter(str.charAt(i-1))))
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
        
       if (!word.equals("")){
        insert(word,line,++position);
            word = "";
        }
    }
    void insert(String word, int line, int position){
        if (head == null){
            head = new Node(word, line, position, word.length());
            current = head;
        }else{
            current.next = new Node(word, line, position, word.length());
            current = current.next;
        }
        length++;
    }
    void calculateOccurance(){
        Node temp;
        current = head;
        for(int i=0; i< length; i++){
            temp = current.next;
            if(current.unique){
                current.occurrence++;
            }
            for(int j = i + 1; j < length; j++){
                if(current.equals(temp)){
                    current.occurrence++;
                    temp.unique = false;
                }
                temp = temp.next;
            }
            current = current.next;
        }


    }

    int getLength(){
        return length;
    }
    void print(){
        current = head;
         
        while (current != null){
            current.print();
            current = current.next;
        }
        System.out.println("length: " + length +"\noccurrence of (is): " + occurrenceNumber("is"));
        System.out.println("unique: " + uniqueWordsNumber());
        
    }
   
    int uniqueWordsNumber(){
        int uniqueWords = 0;
        current = head;
        for (int i = 0; i < length; i++){
           if(current.unique) 
                uniqueWords++;
            current = current.next;
        }
        return uniqueWords;
    }

    
    int occurrenceNumber(String word){
        current = head;
        for (int i = 0; i < length; i++){
            if(current.data.equals(word))
                return current.occurrence;
            current = current.next;
        }
        return 0;
    }
    
    int wordsWithLength(int wLength){
        int count = 0; 
        current = head ; 
        while(current!=null){
            if ((current.length)==wLength){
                count++; 
                current= current.next ;  
            }
        }
        return count; 
        
    }
    /* 
    String sortByOccurrence(){

    }
    */
    String location(String word){
        String s = "not found " ; 
        current = head ; 
        while(current!=null)
        {
            if (current.data.equals(word))
            break;
        }
        
        s="("+ current.line +","+ current.position+")" ; 
        return s ; 

    }

    /* 
    boolean isAdjacent(String word1, String word2){
        
    }

    */
}
    

