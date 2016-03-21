/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Speck
 */

public class Puzzle4 {
    public static int[][] minionlist;
    public static double time;
    public static int[] answer;
    //constructor cuz why not
    public Puzzle4(){
        
    }
    /**
     * @param minions 2d array of minions with [interrogation time, probability numerator,probability denominator]
     * @return most optimal order to interrogate minions (shortest avg time)
     * 2 people time = ((denominator-numerator)*(time of minion and all before)+numerator*timeofminion)/denominator
     * time =
     */
    public static int[] answer(int[][] minions) { 
        int[] answers = new int[minions.length];
        answer = new int[minions.length];
        minionlist=minions;
        time=Double.MAX_VALUE;
        for(int i=0;i<answers.length;i++){
            answers[i]=i;
        }
        permute(answers,0);
        return answer;
    } 
    //random function prob*denominator/time
    public static int[] ranTime(int[][] minions,int denominator){        
        double prob;
        double num=0;
        double[] doublearr=new double[minions.length];
        HashMap<Double,Integer> rantimeindex = new HashMap<>();
        for(int i=0;i<minions.length;i++){
            prob=(double)minions[i][1]/(double)minions[i][2];
            double newnum=(double)denominator*prob/(double)minions[i][0];
            rantimeindex.put(newnum, i);
            doublearr[i]=-newnum;
        }
        Arrays.sort(doublearr);
        int[] answerarray=new int[minions.length];
        for(int i=0;i<minions.length;i++){
            answerarray[i]=rantimeindex.get(-doublearr[i]);
        }
        return answerarray;
    }
    
    public static void permute(int[] arr, int k){
        for(int i = k; i < arr.length; i++){
            swap(arr,i,k);
            permute(arr, k+1);
            swap(arr,k,i);
        }
        if (k == arr.length -1){
            if(time>avgTime(minionlist,arr,0,0)){
                time = avgTime(minionlist,arr,0,0);
                answer=arr.clone();
            }
        }
    }
    public static void swap(int[] answer,int i, int j){
        int pholder=answer[j];
        answer[j]=answer[i];
        answer[i]=pholder;
    }

    public static int[] combinationSearch(int[][]minions){
        int[] answers = new int[minions.length];
        int[] answer = new int[minions.length];
        for(int i=0;i<answers.length;i++){
            answers[i]=i;
        }
        double time=Double.MAX_VALUE;
        for(int j=0;j<answers.length;j++){
            swap(answers,0,j);
            for(int k=0;k<answers.length;k++){
                swap(answers,j,k);
                if(avgTime(minions,answers,0,0)>=time){   
                }
                else{
                    time = avgTime(minions,answers,0,0);
                    answer = answers.clone();
                }
                swap(answers,j,k);
            }
            swap(answers,0,j);
        }
        
        return answer;
    }
    public static double avgTime(int[][] minions,int[] answer,int previoustime,int index){
        double returntime=0;
        if(index>=answer.length-1){
            return previoustime+minions[answer[index]][0];
        }
        else{
            previoustime+=minions[answer[index]][0];
            double prob=((double) minions[answer[index]][1]/(double) minions[answer[index]][2]);
            returntime=(double) previoustime * prob +(1.-prob)*avgTime(minions,answer,previoustime,index+1);
        }
        return returntime;
    }
    
    public static int[] worstTime(int[][] minions,int[][] originalminions){
        int timesum=0;
        double prob;
        double num=Double.MAX_VALUE;
        int besttime=0;
        int[] returnarr=new int[2];
        for(int[] minion : minions){
            timesum+=minion[0];
        }
        for(int i=0;i<minions.length;i++){
            prob=(double)minions[i][1]/(double)minions[i][2];
            double newnum=(double)minions[i][0]*prob+(timesum*(1-prob));
            if(newnum<num){
                num=newnum;
                besttime=i;
            }
            //System.out.println(newnum);
        }
        returnarr[0]=besttime;
        besttime=Arrays.asList(originalminions).indexOf(minions[besttime]);
        returnarr[1]=besttime;
        //System.out.println(besttime);
        return returnarr;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //.2964, .3706, .9990, .592
        //115.625  254.2618 275.73046875 117.808
        //0.1129150390625   0.24830261  0.115046875
        int[] answer=new int[]{0,1,2,3};
        int[][] minion1 = {{5, 1, 5}, {10, 1, 2}};
        int[][] minions = {{390, 185, 624}, {686, 351, 947}, {276, 1023, 1024}, {199, 148, 250}};
        System.out.println(Arrays.toString(answer(minion1)));
        System.out.println(Arrays.toString(answer(minions)));
    }
    
}
