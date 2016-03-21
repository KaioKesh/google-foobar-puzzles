/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


/**
 *
 * @author Speck
 */
public class Answer {
    public Answer(){
        
    }
    
    public static int answer(int food, int[][] grid) { 
        int[][] diff = griddiff(grid);
        ArrayList<int[]> paths=new ArrayList<>();
        int base=baseline(grid);
        int answer=0;
        HashSet<Integer> possfood = new HashSet<>();
        nodediff(paths,diff);
        potentialfood(paths, possfood,0,0,0);
        System.out.println(possfood.toString());
        ArrayList<Integer> sortfood=new ArrayList<>();
        for(int i:possfood){
            sortfood.add(i+base);
        }
        Collections.sort(sortfood);
        Collections.reverse(sortfood);
        for(int i:sortfood){
            if(i<=food){
                answer=i;
                break;
            }
        }
        if(answer==0){
            return -1;
        }
        return food-answer;
    }
    //horizontal sum
    public static int sum(int[] i){
        int sum=0;
        for(int j:i){
            sum+=j;
        }
        return sum;
    }
    //vertical sum
    public static int sum(int[][] i,int index){
        int sum=0;
        for(int[] j:i){
            sum+=j[index];
        }
        return sum;
    }
    
    //food on path going straight right and down
    public static int baseline(int[][]grid){
        int baseline=0;
        for(int i:grid[0]){
            baseline+=i;
        }
        for(int[] j:grid){
            baseline+= j[j.length-1];
        }
        baseline-=grid[0][grid.length-1];
        return baseline;
    }
    //find difference between each potential path and the baseline path
    //2d array of diff top and right should always be 0
    public static int[][] griddiff(int[][] grid){
        int[][] sub=new int[grid.length][grid.length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                int x=i+j;
                if(x>=grid.length){
                    x-=grid.length;
                    sub[i][j]=grid[i][j]-grid[x+1][grid.length-1];
                }
                else{
                    sub[i][j]=grid[i][j]-grid[0][x];
                }
            }
        }        
        return sub;       
    }
    //turn 2d array on it's side 123..n..321
    //use arraylist since array can not contain different size variables
    public static void nodediff(ArrayList<int[]> paths,int[][] grid){
        while(paths.size()!=(2*grid.length-1)){
            for(int i=0;i<grid.length;i++){
                int[] ithsteps= new int[i+1];
                for(int j=0;j<ithsteps.length;j++){
                    ithsteps[j]=grid[j][i-j];
                }
                paths.add(ithsteps);
            }
            for(int i=grid.length-1;i>0;i--){
                int k=grid.length-i;
                int[] ithsteps= new int[i];
                for(int j=0;j<ithsteps.length;j++){
                    ithsteps[j]=grid[j+k][grid.length-j-1];
                }
                paths.add(ithsteps);
                k++;
            }
        }
    }
    public static void potentialfood(ArrayList<int[]> paths, HashSet<Integer> foodeaten,int k,int sum,int index){
        if(k==paths.size()-1){
            foodeaten.add(sum);
        }
        //for(int i = index; i < paths.get(k).length; i++){

            if(k>=(paths.size()-1)/2){
                if(index!=paths.get(k).length-1){
                    potentialfood(paths,foodeaten,k+1,sum+paths.get(k+1)[index],index);
                }
                if(index!=0){
                    potentialfood(paths,foodeaten,k+1,sum+paths.get(k+1)[index-1],index-1);
                }
            }
            else{
                potentialfood(paths,foodeaten,k+1,sum+paths.get(k+1)[index],index);
                potentialfood(paths,foodeaten,k+1,sum+paths.get(k+1)[index+1],index+1);
            }
        //}
    }
        public static void main(String[] args) {
        int food1 = 7;
        int[][] grid1=new int[][]{
            {0,2,5}, //7
            {1,1,3}, //5
            {2,1,1}}; //4
            //3 4 9
        //4,5,6,7,11
        //11 -4-5-6-7
        //sum 16 avg 1.777777
        System.out.println(answer(food1,grid1));
        int food2 = 12;
        System.out.println(answer(food2,grid1));
        
        int food3=100;
        int[][] grid3=new int[][]{
            {0,1,20,3}, // 24 
            {2,2,3,4}, // 11
            {2,1,1,5},// 9
            {1,1,7,3}}; //12
        // 5,5,31,15
        //13,14,15,16,17,18,19,33,35,36
        //sum 56 avg = 3.5
        System.out.println(answer(food3,grid3));
        
        int food4=100;
        int[][] grid4=new int[][]{
            {0,1,2,3}, //6   
            {4,5,6,7}, //22
            {8,9,10,11},// 38
            {12,13,14,15}}; //54
        //   24,28,32,36
        //39,42,45,48,51,54,57,60,63,66
        //sum 120 avg 7.5
        System.out.println(answer(food4,grid4));
        
        int food6=100;
        int[][] grid6=new int[][]{
            {0,5,6,7},      //18
            {15,4,1,8},     //28
            {14,3,2,9},     //28
            {13,12,11,10}}; //46
        //  42 24 20 34
        // 31,33,35,37,39,41,43,45,47,53,55,65,75
        //+2+4+8+10+20+30
        //-2-4-6-8-10-12-14
        // 120 7.5
//        System.out.println(answer(food6,grid6));
//        
//        int[][] subtraction=griddiff(grid4);
//        for(int[]sub4:subtraction)
//            System.out.println(Arrays.toString(sub4));
//        System.out.println("new grid");
//        subtraction=griddiff(grid6);
//        for(int[]sub6:subtraction)
//            System.out.println(Arrays.toString(sub6));
//        System.out.println("new grid");
//        subtraction=griddiff(grid1);
//        for(int[]sub3:subtraction)
//            System.out.println(Arrays.toString(sub3));
    }
}
