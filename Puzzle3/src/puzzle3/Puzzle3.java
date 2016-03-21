/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Speck
 * Answer takes in array of size 2 arrays with start and finish times of meetings
 * then sorts this array based on the finish times
 * then creates a list that starts at lowest start time
 * and finds the next lowest start time
 * since list is sorted on finish time, will always get
 * the lowest interval meeting and should end up.
 */
public class Puzzle3 {
    public Puzzle3(){
        
    }
    public static int Answer(int[][] meetings){
        
        Arrays.sort(meetings, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof int[] && o2 instanceof int[]){
                    int[] a_1=(int[]) o1;
                    int[] a_2=(int[]) o2;
                    if(a_1[0]==a_2[0] && a_1[1]==a_2[1])
                        return 0;
                    if(a_1[1]>a_2[1])
                        return 1;                 
                }
                return -1;
            }
        });
        int x = createList(meetings).length-numzeroarray(createList(meetings));
//        int[][][] possibleschedule=new int[10000][meetings.length][2];
//        int k=0;
//        int finish=0;
//        Boolean done=false;
//        for(int[] xmeeting : meetings){
//            possibleschedule[k][0]=xmeeting;
//            int i=0;
//            //while(!done){
//                for(int[] ymeeting : meetings){
//                    if(i+1<possibleschedule[k].length){
//                        if(possibleschedule[k][i][1]<=ymeeting[0]){
//                            i++;
//                            possibleschedule[k][i]=ymeeting;                        
//                        }
//                    }
//                }
//            //}
//            k++;
//        }
//        int x=0;
//        int meetlength=0;
//        int[] zerocheck=new int[]{0,0};
//        for(int[][] zmeeting:possibleschedule){
//            int y=0;
//            for(int[] ameeting:zmeeting){
//                //cuz initialized to meeting.length will always get same length
//                //unless subtract from it
//                if(Arrays.equals(ameeting,zerocheck)){
//                    y++;
//                }
//            }
//            if((zmeeting.length-y)>x){
//                x=zmeeting.length-y;
//            }
//        }
        return x;
    }
    //give a sorted meeting list
    public static int[][] createList(int[][] sortedmeetings){
        int finish=0;
        int[][] returnmeetings= new int[sortedmeetings.length][2];
        int j=0;
        for(int i=0; i<sortedmeetings.length;i++){
            if(finish<=sortedmeetings[i][0]){
                finish=sortedmeetings[i][1];
                returnmeetings[j]=sortedmeetings[i];
                j++;
            }
        }
        return returnmeetings;
    }
    public static int numzeroarray(int[][] meetings){
        int[] zerocheck=new int[]{0,0};
        int y=0;
        for(int[] meeting:meetings){
            if(Arrays.equals(meeting,zerocheck)){
                y++;
            }
        }
        return y;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] testcase = { {0,3},{2,4},{4,5},{3,1000},{1000,1001}};
        //3
        int output = Answer(testcase);
        System.out.println(output);
        
    }
    
}
