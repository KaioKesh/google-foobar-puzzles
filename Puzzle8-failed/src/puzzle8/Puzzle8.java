/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 *
 * @author Speck
 */
public class Puzzle8 {

    //sequence of numbers, how many other sequences have same tree
    //1 3 6 10 15 21 28 36 45
    //59821 6 59218 59281 52198 52918 52981
    // 4123567
    //12345678910 0+1 9 0
    //21345678910 8+1 8 1     45 8 2 
    //31245678910 87654321 36  7 2  7*8/2+7+1 8*9/2
    //41235678910 6 3 84  7654321 654321 54321 4321 321 21 1 8*7 7*6 6*5 5*4 4*3 3*2 /2 +1 
    //51234678910 5 4 126 654321 54321 4321 321 21 1  54321 4321 321 21 1  4321 321 21 1 321 21 1 21 1 1
    //61 2 345 78910 4 5 (54321 4321 321 21 1)3,4,5  2(4321 321 21 1 321 21 1 21 1 1)35 70+35+15+5+1
    //6123457891011 5 5
    //66
    //678954 678594 678549 675489 675894 675849 654789 657489 657849 657894 10
    // 2 2 6 2*(2 1) 3 2 10 
    //n*n+1/2 + 
    public static HashMap<String,Long> countsave = new HashMap<>();
    public static String answer(int[] seq){
        int[] counter = count(seq);
        int older = counter[0];
        int younger = counter[1];
        long num;
            num = combinations(older,younger);
        return num+"";
    }
    public static int[] count(int[] seq){
        int older = 0;
        int younger = 0;
        for(int i=1;i<seq.length;i++){
            if(seq[i]>seq[0]){
                older++;
            }
            else{
                younger++;
            }
        }
        int[] counter = {older,younger};
        return counter;
    }
    public static int[] splitleft(int[] seq){
        int[] left = new int[count(seq)[0]];
        int j=0;
        for(int i=1;i<seq.length;i++){
            if(seq[i]>seq[0]){
                left[j]=seq[i];
                j++;
            }
        }
        return left;
    }
    public static int[] splitright(int[] seq){
        int[] right = new int[count(seq)[1]];
        int j=0;
        for(int i=1;i<seq.length;i++){
            if(seq[i]<seq[0]){
                right[j]=seq[i];
                j++;
            }
        }
        return right;
    }
    public static long combinations(int larger, int smaller){
        String key;
        if(larger>smaller){
            key = larger+" "+smaller;
        }else{
            key = smaller+" "+larger;
        }
        if(countsave.containsKey(key)){
            return countsave.get(key);
        }
        if(smaller==0||larger==0){
            return 1;
        }
        if(smaller==1){
            return larger+1;
        }
        if(smaller==2){
            return (larger+1)*(larger+2)/2;
        }
        long x=0;
        for(int i=0;i<larger+1;i++){
            x+=combinations(i,smaller-1);
        }
        countsave.put(key, x);
        return x;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] input = {5,1,2,3,4,6,7,8,9,10};
        int[] input1 = {6,1,2,3,4,5,7,8,9,10};
        int[] input2 = {6,1,2,3,4,5,7,8,9,10,11};
        int[] input3 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
        int[] input4 = {27,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
        int[] input5 = {10,1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19};
        System.out.println(answer(input4));
        //System.out.println(combinations(10,5));
        //System.out.println(combinations(10,4));
        System.out.println(combinations(26,23));
        //System.out.println(combinations(5,4));

        
    }
    
}
