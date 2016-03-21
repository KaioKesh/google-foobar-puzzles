/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Speck
 * take each letter find the number of unique letters that proceed it, sort by number of letters proceeding it.
 */
public class Puzzle7 {

    public static List<String> dictionary = new ArrayList<>();
    public static HashMap<String,String> precede = new HashMap<>();
    public static String answer(String[] words) { 
        String answer="";
        precede.clear();
        String longstr="";
        //string of all words
        for(String word:words){
            longstr+=word;
        }
        //remove duplicate chars
        //regex taken from a stack overflow thread
        String letters = longstr.replaceAll("(.)(?=.*?\\1)", "");
        //java 7 does not use get or default so initialize all the used values here
//        for(char x:letters.toCharArray()){
//                precede.put(x+"", "");
//        }
        loopcut(words);
        
        extendprecede(letters);

        for(int i=0;i<letters.length();i++){
            for(char x:letters.toCharArray()){
                if(precede.get(x+"").length()==i){
                    answer+=x;
                    //should only be one of each length
                    break;
                }
            }
        }
        return answer;
    } 
    //checks each letter's precedents and adds their precedents the letter's
    //continues until there is a an array of precedent lengths equal to 0..n
    public static void extendprecede(String letters){
        int[] target=new int[letters.length()];
        for(int i=0;i<letters.length();i++){
            target[i]=i;
        }
        int[] current=simplify(letters);
        while(!Arrays.equals(current, target)){
            for(char x:letters.toCharArray()){
                String input=precede.get(x+"");
                for(char y:precede.get(x+"").toCharArray()){
                    input+=precede.get(y+"");
                }
                input=input.replaceAll("(.)(?=.*?\\1)", "");
                precede.put(x+"", input);
            }
            current=simplify(letters);
        }
        
    }
    //removes duplicates from each precedent's hashmap
    //returns an array of each letter's number of precedents sorted ascending
    public static int[] simplify(String letters){
        int[] simple=new int[letters.length()];
        int i=0;
        for(char x:letters.toCharArray()){
            String input=precede.get(x+"").replaceAll("(.)(?=.*?\\1)", "");
            precede.put(x+"", input);
            simple[i]=input.length();
            i++;
        }
        Arrays.sort(simple);
        return simple;
    }
    //add to precede hashmap by getting first characters of every word 
    // and then adding every letter before that char.
    //getordefault is not implemented in java7
    public static void addprecede(String[] words){
        String word = firstindex(words);
        for(int i=0;i<word.length();i++){
            String c=word.charAt(i)+"";
            String input=precede.getOrDefault(c, "")+word.substring(0,i);
            precede.put(c, input);
        }
    }
    /**
     * @param words String array
     * unused
     * removes the first character of every String in the array
     * 
    */
    public static void strcut(String[] words){
        for(int i=0;i<words.length;i++){
            if(words[i].length()>1)
                words[i]=words[i].substring(1);
            if(words[i].length()==1)
                words[i]="";
        }
    }
    /**
     * @param words String array
     * @param c the character that we will be taking
     * @return String array
     * takes only the Strings that start with char c from words
     * purpose was to loop through words for each char then recursively shrink the array
    */
    public static String[] arraycut(String[] words,char c){
        ArrayList<String> tempwords=new ArrayList<>();
        for(String word:words){
            if(word.length()>0){
                if(word.charAt(0)==c){
                    tempwords.add(word.substring(1));
                }
            }
        }
        String[] newwords= new String[tempwords.size()];
        tempwords.toArray(newwords);
        return newwords;
    }
    /**
     * @param words String array
     * recursive call on words that have same first character addprecede
     * each call cuts off first character
     * precedents added before array is cut
     * ends whenever there is only one word with that starting character as no information can be obtained from one word
    */
    public static void loopcut(String[] words){
        if(words.length<=1 ){
            return;
        }
        String letters = firstindex(words);
        addprecede(words);
        for(char x:letters.toCharArray()){
            String[] newwords=arraycut(words,x);
            loopcut(newwords);
        }
    }
    /**
     * @param words String array
     * @return String array
     * return the first unique char of string array
    */
    public static String firstindex(String[] words){
        String dict="";
        for(int i=0;i<words.length;i++){
            if(words[i].length()>0){
                if(dict.indexOf(words[i].charAt(0), 0)==-1){
                    dict+=words[i].charAt(0);
                }
            }
        }
        return dict;
    }
    public int totallength(String[] words){
        int len=0;
        for(String word:words){
            len+=word.length();
        }
        return len;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] words1=new String[]{"y", "z", "xy"};
        String answer1= "yzx";
        String[] words2=new String[]{"ba", "ab", "cb"};
        String answer2= "bac";
        String[] words3=new String[]{"ab", "ac", "bc","bcd","bce","bd"};
        String answer3= "bac";
        System.out.println(answer(words1));
        System.out.println(answer(words2));
        System.out.println(answer(words3));
    }
    
}
