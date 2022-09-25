import java.io.*;
import java.util.Scanner;
public class LinkedList {
    private Node head;
    private Node current;
    private int length;

    LinkedList(String path){
        addFile(path);
 
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
    int getLength(){
        return length;
    }
    void print(){
        current = head;
        /* 
        while (current != null){
            current.print();
            current = current.next;
        }*/
        System.out.println("length: " + length +"\noccurrence of the: " + occurrenceNumber("the"));
        System.out.println("unique: " + uniqueWordsNumber());
        
    }
   
    int uniqueWordsNumber(){// errorsss
        if (length == 0) return 0;
        if (length == 1) return 1;

        int unique = 0;
        
        Node isUnique = head;
        current = head.next;

        for (int i = 0; i < length; i++){
            while(current != null){
                if(isUnique.equals(current) && !(isUnique.same(current)))
                    break;
                current = current.next;
            }
            if(current == null) ++unique;
            current = head;
            isUnique = isUnique.next;
        }
        return unique;
    }

    
    int occurrenceNumber(String word){
        current = head;
        int occurrence = 0;
        for (int i = 0; i < length; i++){
            if(current.data.equals(word))
                occurrence++;
            current = current.next;
        }
        return occurrence;
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
    
    s="("+ temp.line +","+ temp.position+")" ; 
    return s ; 

    }

    /* 
    boolean isAdjacent(String word1, String word2){
        
    }

    */
}
    

