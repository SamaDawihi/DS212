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

    int getLength(){
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
    String sortByOccurrence(){
        int arr[] = new int[length];
        Node tmp = head;
        for (int i=0 ; i<length ;i++){
            arr[i]=tmp.occurrence;
            tmp = tmp.next;
        }//عبيت الاراي
        for (int i=0; i<arr.length-1 ;i++){
            for(int j=0; j<arr.length-1-i ;j++){
                if(arr[j] > arr[j+1]){
                    int place = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = place; 
                }
            }
        } //end sorting
        String sorted = "";
        int max = 0;
        for(int i=arr.length-1; i>=0; i--){
            int value = arr[i];
            if(value != max && value !=0){
                tmp = head;
                while(tmp != null){
                    if(tmp.occurrence == value)
                        sorted += "("+tmp.data+","+value+"),";
                        tmp=tmp.next;
                }
            }
            max = value;
            if(value != 0 ) 
                continue;
            else {
                sorted = sorted.substring(0,sorted.length()-1);
                return sorted;
            }
        }
        sorted = sorted.substring(0,sorted.length()-1);
        return sorted;
    }

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
 
    boolean isAdjacent(String word1, String word2){
        Node p =head , q = head;
        for (int i=0 ; i<length-1 ; i++){
            if (p.data.equalsIgnoreCase(word1) || p.data.equalsIgnoreCase(word2)){
                for (int j= i+1 ; j<length ; j++){
                    q = q.next;
                    if ( (q.data.equalsIgnoreCase(word1) || q.data.equalsIgnoreCase(word2)) ){
                        if (q.line == p.line && (q.position == p.position+1 || p.position == q.position+1))
                            return true;
                        else if (p.occurrence == 1)
                            return false;
                        else 
                            break; }//end inner if
            }//end inner for
            }// end first if
            q = p; 
            p = p.next;
            q= q.next;
        }//end outer for
        return false;
    }//end method
}
    

