/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Speck
 */
public class restart {
    public static int[] answer(int[][] minions) {
        HashMap<int[],Integer> index=new HashMap<>();
        for(int i=0;i<minions.length;i++){
            index.put(minions[i], i);
        }
        Arrays.sort(minions, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof int[] && o2 instanceof int[]){
                    int[] a_1=(int[]) o1;
                    int[] a_2=(int[]) o2;
                    if(timeprob(a_1)==timeprob(a_2))
                        return 0;
                    if(timeprob(a_1)>timeprob(a_2))
                        return 1;                 
                }
                return -1;
            }
        });
        int[] answer=new int[minions.length];
        for(int i=0;i<minions.length;i++){
            answer[i]=index.get(minions[i]);
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
    public static double timeprob(int[] minion){
        double returnnum=0;
        returnnum= (double)minion[1]/(double)minion[2];
        returnnum=(double)minion[0]/returnnum;
        return returnnum;
    }
        public static void main(String[] args) {
        //.2964, .3706, .9990, .592
        //115.625  254.2618 275.73046875 117.808
        //0.1129150390625   0.24830261  0.115046875
        int[][] minion1 = {{5, 1, 5}, {10, 1, 2}};
        int[][] minions = {{390, 185, 624}, {686, 351, 947}, {276, 1023, 1024}, {199, 148, 250}};
        System.out.println(timeprob(new int[]{390,185,624}));
        System.out.println(timeprob(new int[]{686, 351, 947}));
        System.out.println(timeprob(new int[]{276, 1023, 1024}));
        System.out.println(timeprob(new int[]{199, 148, 250}));
        System.out.println(Arrays.toString(answer(minions)));
    }
}
