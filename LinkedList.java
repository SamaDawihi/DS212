import java.io.*;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
public class LinkedList {
    private Node head;
    private Node current;
    private int length; //عدد الكلمات

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
            BufferedReader reader2 = new BufferedReader(reader); //i a-m a student/n in king saud uni
            try{
                while(true){


                    if (!word.equals("")){ // عشان يضيف اخر كلمة بالسطر
                        insert(word,line,++position);
                            word = "";
                    }
                    str = reader2.readLine();
                    if(str == null) break; //وصلنا اخر السطر
                    line++;
                    position = 0;

                    for(int i = 0; i < str.length(); i++){ //نمشي على حرف حرف

                        if(Character.isLetter(str.charAt(i))){ // حرف
                            word += str.charAt(i);
                        }

                        else if(Character.isWhitespace(str.charAt(i))){
                            insert(word,line,++position);
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
        for(int i=0; i< length; i++){  //نمشي على عدد النودز
            temp = current.next;
            if(current.unique){ //التكرار فقط لاول ظهور
                current.occurrence++;
            for(int j = i + 1; j < length; j++){
                if(current.equals(temp)){
                    current.occurrence++;
                    temp.unique = false;
                }
                temp = temp.next;
            }
        } //we have to try it 
            current = current.next;
        }
    }

    int getTotalNumberOfWords(){ //Operation 1
        return length;
    }
    
    void print(){ //just for test
        current = head;
         
        while (current != null){
            current.print();
            current = current.next;
        }
        System.out.println("length: " + length +"\noccurrence of (is): " + occurrenceNumber("is"));
        System.out.println("unique: " + uniqueWordsNumber());
        
    }
   
    int uniqueWordsNumber(){ //Operation 2
        int uniqueWords = 0;
        current = head;
        for (int i = 0; i < length; i++){
           if(current.unique) 
                uniqueWords++;
            current = current.next;
        }
        return uniqueWords;
    }

    
    int occurrenceNumber(String word){ //Operation 3
        current = head;
        while(current != null){
            if(current.data.equals(word))
                return current.occurrence;
            current = current.next;
        }
        return 0;
    }
    
    int wordsWithLength(int wLength){ //Operation 4
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
    
    String sortByOccurrence(){ //Operation 5
        int max = 0;
        current = head;
        while(current!=null){ // big oh (n)
            if (current.occurrence > max){
                max = current.occurrence;
                current = current.next;
            }
        }
        String sorted = "";
        while (max > 0){ // big oh (n) * max occurrence ==> n^2
            current = head;
            while(current != null){ 
                if(current.occurrence == max)
                    sorted += "("+current.data+","+max+"),";
                current = current.next;
            }    
            max--;
        }
        sorted = sorted.substring(0,sorted.length()-1);
        return sorted;
    }

    String location(String word){ //Operation 6
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
 
    boolean isAdjacent(String word1, String word2){ //Operation 7
        Node p =head , q = head;
        for (int i = 0 ; i < length - 1 ; i++){
            if (p.data.equalsIgnoreCase(word1)){
                q = p.next;
                if(q.data.equalsIgnoreCase(word2)&&(q.line == p.line))
                    return true;
            }// end first if to check word1
            else if (p.data.equalsIgnoreCase(word2)){
                q = p.next;
                if(q.data.equalsIgnoreCase(word1)&&(q.line == p.line))
                    return true;
            }// end second if to check word2
            p = p.next;
        }//end for loop
        return false;
    }//end method
}
    

