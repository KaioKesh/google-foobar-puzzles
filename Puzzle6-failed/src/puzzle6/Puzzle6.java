/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle6;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Speck
 */
public class Puzzle6 {
//number of possible paths
    public static final int[] numpaths={0,0,2,6,20,70,252,924,3432,12870,48620,184756,705432,2704156,10400600,40116600,155117520,601080390};
    //N=18 greater than int size 2333606220
    //19 9075135300
    //20 35345263800
    public Puzzle6(){
        
    }
    
    public static int answer(int food, int[][] grid) { 
        ArrayList<Integer> paths= new ArrayList<>();
        ArrayList<Integer> answers= new ArrayList<>();
        int answer=0;
        //create list of half 0's and 1's
        for(int i=1;i<grid.length;i++){
            paths.add(0);
        }
        for(int i=1;i<grid.length;i++){
            paths.add(1);
        }
        //online lexical permute code
        
//        do{
//            int x= pathadd(paths,grid,food);
//            if(x==food){
//                answer=x;
//                break;
//            }
//            if(x>answer && x<food){
//                answer=x;
//            }
//            //answers.add(pathadd(paths,grid,food));
//        }
//        while(permuteLex(paths));
        
        
        // original permute code
        permute(paths,0,grid,food,answers);
        int answer2=0;
        for(int a: answers){
            System.out.println("path "+a);
            if(a>answer2 && a<=food){
                answer2=a;
            }
        }
        if(answer2==0)
            return -1;
        return food-answer;
    }
    
    public static int greaterpath(int[][] grid) { 
        int i=0;
        int j=0;
        int foodeaten=0;
        int zeroindsize=grid.length-1;
        
        while(i<zeroindsize || j<zeroindsize){
            if(i>=zeroindsize){
                j++;
            }
            else if(j>=zeroindsize){
                i++;
            }
            else if(grid[i+1][j]>grid[i][j+1]){
                i++;                    
            }
            else{
                j++;                    
            }
            foodeaten+=grid[i][j];
        }
        return foodeaten;
    }
    //given path add up
    public static int pathadd(int[] path,int[][] grid) { 
        int sum=0;
        //coordinates
        int x=0;
        int y=0;
        int xy=0;
        for(int p:path)
            xy+=p;
        if(xy != grid.length-1){
            System.out.println("Error, non-square grid");
            return -1;
        }
        for(int i=0;i<path.length;i++){
            if(path[i]==1){
                y++;
                sum+=grid[x][y];
            }
            else{
                x++;
                sum+=grid[x][y];
            }
        }

        return sum;
    }
    public static int pathadd(int[] path,int[][] grid,int food) { 
        int sum=0;
        //coordinates
        int x=0;
        int y=0;
        int xy=0;
        for(int p:path)
            xy+=p;
        if(xy != grid.length-1){
            System.out.println("Error, non-square grid");
            return -1;
        }
        for(int i=0;i<path.length;i++){
            if(path[i]==1){
                y++;
                sum+=grid[x][y];
            }
            else{
                x++;
                sum+=grid[x][y];
            }
            if(sum>food){
                return -1;
            }
        }

        return sum;
    }
    public static int pathadd(ArrayList<Integer> path,int[][] grid,int food) { 
        int sum=0;
        //coordinates
        int x=0;
        int y=0;
        int xy=0;
        for(int p:path)
            xy+=p;
        if(xy != grid.length-1){
            //System.out.println("Error, non-square grid");
            return -1;
        }
        for(int i=0;i<path.size();i++){
            if(path.get(i)==1){
                y++;
                sum+=grid[x][y];
            }
            else{
                x++;
                sum+=grid[x][y];
            }
            if(sum>food){
                return -1;
            }
        }

        return sum;
    }
    public static void permute(ArrayList<Integer> arr, int k,int[][] grid,int food,ArrayList<Integer> lengths){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1,grid,food,lengths);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            lengths.add(pathadd(arr,grid,food));
        }
    }
    public static boolean permuteLex(ArrayList<Integer> data) {
    int k = data.size() - 2;
    while (data.get(k) >= data.get(k + 1)) {
        k--;
        if (k < 0) {
            return false;
        }
    }
    int l = data.size() - 1;
    while (data.get(k) >= data.get(l)) {
        l--;
    }
    java.util.Collections.swap(data, k, l);
    int length = data.size() - (k + 1);
    for (int i = 0; i < length / 2; i++) {
        java.util.Collections.swap(data, k + 1 + i, data.size() - i - 1);
    }
    return true;
}
    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        int food1 = 7;
        int[][] grid1=new int[][]{
            {0,2,5}, //7   left 3 right 9
            {1,1,3}, //5
            {2,1,1}}; //4
        System.out.println(answer(food1,grid1));
        int food2 = 12;
        System.out.println(answer(food2,grid1));
//        
//        int food3=100;
//        int[][] grid3=new int[][]{
//            {0,1,20,3}, //7   left 3 right 9
//            {2,2,3,4}, //5
//            {2,1,1,5},
//            {1,1,7,3}}; //4
//        System.out.println(answer(food3,grid3));
//        
//        int food4=100;
//        int[][] grid4=new int[][]{
//            {0,1,2,3}, //7   left 3 right 9
//            {4,5,6,7}, //5
//            {8,9,10,11},
//            {12,13,14,15}}; //4
//        System.out.println(answer(food4,grid4));
//        
//        int food5=100;
//        int[][] grid5=new int[][]{
//            {0,4,8,12}, //7   left 3 right 9
//            {1,5,9,13}, //5
//            {2,6,10,14},
//            {3,7,11,15}}; //4
//        System.out.println(answer(food5,grid5));
//        
//        int food6=100;
//        int[][] grid6=new int[][]{
//            {0,5,6,7}, //7   left 3 right 9
//            {15,4,1,8}, //5
//            {14,3,2,9},
//            {13,12,11,10}}; //4
//        System.out.println(answer(food6,grid6));

    }
    
}
